package bokzip.back.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SuccessResponse<T> {
    private String successMessage;

    // @param : Success Message를 전송할 때 사용 (스크랩 시 통일된 json 형태로 메세지 보내기 위함)
    public static SuccessResponse res(final String successMessage) {
        return SuccessResponse.builder()
                .successMessage(successMessage)
                .build();
    }
}

