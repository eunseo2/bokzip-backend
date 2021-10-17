package bokzip.back.config.error;

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

    //401 Unauthorized
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인시 가능합니다."),

    //404 Not Found 찾을 수 없는 리소스
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청 받은 리소스를 찾을 수 없습니다."),

    NO_DATA(HttpStatus.NOT_FOUND, "스크랩이 존재하지 않습니다."),

    //409 Conflict 이미 데이터가 존재할 경우
    VALID_SCRAP(HttpStatus.CONFLICT, "이미 스크랩 했습니다."),

    //500 INTERVAL_SERVER
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bokzip 서버 에러입니다.");

    private final HttpStatus httpStatus;
    private final String msg;
}
