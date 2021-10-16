package bokzip.back.config;

import bokzip.back.domain.User;
import bokzip.back.dto.UserDto;
import bokzip.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    /**
     * @param userRequest : 현재 어떤 서비스를 통해 로그인이 이루어졌는지 확인 -> 전달된 값들을 Map<String, Object>형태로 사용
     * @return 전달된 값들을 Map<String, Object>
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //@param : 현재 로그인 진행 중인 서비스 구분하는 코드. 네이버인지, 구글인지 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //@param : OAuth2 로그인 진행 시 key가 되는 필드 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //@param : OAuth2UserService를 통해 가져온 OAuth2User의 attribute
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        //@param : 세션에 사용자 정보를 저장하기 위한 dto 클래스
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new UserDto(user)); // SessionUser (직렬화된 dto 클래스 사용)

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttribute(),
                attributes.getNameAttributeKey());

    }

    //@param : 유저 생성 및 수정 서비스 로직
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getProfile()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
