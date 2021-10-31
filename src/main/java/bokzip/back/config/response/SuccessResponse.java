package bokzip.back.config.response;

import bokzip.back.dto.ScrapMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class SuccessResponse<T> {
    private final String message;
    private final List<ScrapMapping> data;;

    // @param : Success Message를 전송할 때 사용 (스크랩 시 통일된 json 형태로 메세지 보내기 위함)
    public static SuccessResponse res(final String message) {
        return SuccessResponse.builder()
                .message(message)
                .build();
    }
    public static SuccessResponse res(final String message, final List<ScrapMapping> data) {
        return SuccessResponse.builder()
                .message(message)
                .data(data)
                .build();
    }
}

