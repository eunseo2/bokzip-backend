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

    //401 UnAuthorized 유효한 인증 자격이 없으므로 요청이 적용되지 않음
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "유효한 인증 자격이 없어 요청이 적용되지 않았습니다."),

    //403 Forbidden 요청이 서버에 잘 전달되었지만, 권한에 의해 거절되었음
    FORBIDDEN(HttpStatus.FORBIDDEN, "해당 사용자에게 권한이 허용되지 않은 정보입니다."),

    //404 Not Found 찾을 수 없는 리소스
    NO_DATA(HttpStatus.NOT_FOUND, "요청 받은 리소스를 찾을 수 없습니다."),

    //409 Conflict 이미 데이터가 존재할 경우
    VALID_SCRAP(HttpStatus.CONFLICT, "이미 스크랩 했습니다."),

    //500 INTERVAL_SERVER
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bokzip 서버 에러입니다.");

    private final HttpStatus httpStatus;
    private final String msg;
}
