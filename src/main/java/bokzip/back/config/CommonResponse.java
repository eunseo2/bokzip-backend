package bokzip.back.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private String message;
    private String accessToken;
    private String refreshToken;

    // @param : 로그인 성공 시 성공메세지와 token을 전송할 때 사용
    public static CommonResponse res(final String message, final String accessToken, final String refreshToken) {
        return CommonResponse.builder()
                .message(message)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
