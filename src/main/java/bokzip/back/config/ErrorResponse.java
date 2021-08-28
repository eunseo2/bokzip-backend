package bokzip.back.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse<T> {
    private String errorMessage;

    // @param : Error Message를 전송할 때 사용
    public static ErrorResponse res(final String errorMessage) {
        return ErrorResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }
}

