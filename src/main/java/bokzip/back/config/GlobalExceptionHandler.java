package bokzip.back.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice //@Controller 전역에서 발생할 수 있는 예외를 잡아 처리
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @see : @ExceptionHandler : 특정 Exception을 지정해서 별도로 처리해줌
     */

    @ExceptionHandler(RuntimeException.class)
    public static ResponseEntity errorhandler(RuntimeException e){
        ErrorCode errorCode;
        switch (e.getMessage()){
            case "400" :
                log.error("400 Bad Request", e);
                errorCode = ErrorCode.INVALID_VALUE;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());

            case "401" :
                log.error("401 UnAuthorized", e);
                errorCode = ErrorCode.UNAUTHORIZED;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());

            case "403":
                log.error("403 Forbidden", e);
                errorCode = ErrorCode.FORBIDDEN;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());

            case "404":
                log.error("404 Not Found Error", e);
                errorCode = ErrorCode.NO_DATA;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());

            case "409" :
                log.error("409 Conflict", e);
                errorCode = ErrorCode.VALID_SCRAP;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());

            default:
                log.error("500 server error", e);
                errorCode = ErrorCode.SERVER_ERROR;
                return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
        }
    }

    //@param : controller로 들어오는 value 타입이 잘못된 경우 호출
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public static ResponseEntity missmatchException(MethodArgumentTypeMismatchException e) {
        log.error("400 Bad Request", e);
        ErrorCode errorCode = ErrorCode.INVALID_VALUE;
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }
}
