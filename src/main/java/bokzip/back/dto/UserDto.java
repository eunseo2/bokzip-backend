package bokzip.back.dto;

import bokzip.back.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@ToString
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String profile;
    private Provider provider;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.provider = user.getProvider();
    }
}
