package bokzip.back.dto;

import lombok.Builder;
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

    @Builder
    public UserDto(String name, String email, String profile, String role){
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.role = role;
    }
}
