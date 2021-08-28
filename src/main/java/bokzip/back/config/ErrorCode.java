package bokzip.back.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * @see : 예외케이스 정의
     */

    //400 Bad Request 잘못된 요청
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 다시 요청해주세요."),

    //404 Not Found 찾을 수 없는 리소스
    NO_DATA(HttpStatus.NOT_FOUND, "요청 받은 리소스를 찾을 수 없습니다."),

    //500 INTERVAL_SERVER
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bokzip 서버 에러입니다.");

    private final HttpStatus httpStatus;
    private final String msg;
}
