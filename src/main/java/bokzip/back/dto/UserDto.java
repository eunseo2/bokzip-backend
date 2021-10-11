package bokzip.back.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserDto implements Serializable {
    private String name;
    private String email;
    private String profile;
    private String role;
}
