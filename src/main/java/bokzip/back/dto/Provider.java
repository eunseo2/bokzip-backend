package bokzip.back.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    GOOGLE("ROLE_GOOGLE", "구글");
    private final String key;
    private final String title;
}
