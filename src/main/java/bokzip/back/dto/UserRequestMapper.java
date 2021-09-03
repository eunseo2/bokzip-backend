package bokzip.back.dto;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRequestMapper {
    public UserDto toDto(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return UserDto.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .profile((String)attributes.get("profile"))
                .role((String)attributes.get(Provider.GOOGLE.getTitle()))
                .build();
    }
}
