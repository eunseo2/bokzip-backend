package bokzip.back.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.NullServiceException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice //@Controller 전역에서 발생할 수 있는 예외를 잡아 처리
@Slf4j
public class GlobalExceptionHandler {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @see : @ExceptionHandler : 특정 Exception 을 지정해서 별도로 처리해줌
     */

    //400
    @ExceptionHandler({
            EntityNotFoundException.class, MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class, ServletRequestBindingException.class,
            TypeMismatchException.class
    }) //파라미터 검증 실패 (@Vaildate)
    public static ResponseEntity BadRequestError(){
//        logger.log(Level.INFO, "400 bad request", nve);
        ErrorCode errorCode = ErrorCode.INVALID_VALUE;
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }

    //404
    @ExceptionHandler({Exception.class, ChangeSetPersister.NotFoundException.class})
    public static ResponseEntity NotFoundError(){
//        logger.log(Level.INFO, "404 not found", nfe);
        ErrorCode errorCode = ErrorCode.NO_DATA;
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }

    //500
    @ExceptionHandler({AsyncRequestTimeoutException.class})
    public static ResponseEntity ServerError(){
//        logger.log(Level.INFO, "500 server error", ex);
        ErrorCode errorCode = ErrorCode.SERVER_ERROR;
        return new ResponseEntity<>(ErrorResponse.res(errorCode.getMsg()), errorCode.getHttpStatus());
    }
}
