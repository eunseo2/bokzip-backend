package bokzip.back.config.error;

import bokzip.back.config.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice //@Controller 전역에서 발생할 수 있는 예외를 잡아 처리
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @see : @ExceptionHandler : 특정 Exception을 지정해서 별도로 처리해줌
     */
    private static ErrorCode errorCode;

    @ExceptionHandler(CustomException.class)
    public static ResponseEntity customException(CustomException e){
        errorCode = e.getErrorCode();
        log.error(String.valueOf(errorCode.getHttpStatus()), errorCode.getMsg());
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }

    //@param : controller로 들어오는 value 타입이 잘못된 경우 호출
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public static ResponseEntity missmatchException() {
        errorCode = ErrorCode.INVALID_VALUE;
        log.error(String.valueOf(errorCode.getHttpStatus()), errorCode.getMsg());
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }

    //@param : DB에 해당 값이 없을 경우 NoSuchElementException 호출
    @ExceptionHandler(NoSuchElementException.class)
    public static ResponseEntity nosuchException(){
        errorCode = ErrorCode.NOT_FOUND;
        log.error(String.valueOf(errorCode.getHttpStatus()), errorCode.getMsg());
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity serverException(){
        errorCode = ErrorCode.SERVER_ERROR;
        log.error(String.valueOf(errorCode.getHttpStatus()), errorCode.getMsg());
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }
}
