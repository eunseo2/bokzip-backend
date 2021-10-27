package bokzip.back.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse<T> {
    private String message;
    private Object data;

    // @param : Error Message를 전송할 때 사용
    public static ErrorResponse res(final String message) {
        return ErrorResponse.builder()
                .message(message)
                .build();
    }
    public static ErrorResponse res2(final String message, final Object data) {
        return ErrorResponse.builder()
                .message(message)
                .data(data)
                .build();
    }
}

