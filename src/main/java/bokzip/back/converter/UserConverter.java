package bokzip.back.converter;

import bokzip.back.domain.User;
import bokzip.back.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User converterUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setProfile(userDto.getProfile());
        user.setProvider(userDto.getProvider());
        return user;
    }
}
