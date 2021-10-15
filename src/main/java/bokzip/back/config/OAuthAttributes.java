package bokzip.back.config;


import bokzip.back.domain.Provider;
import bokzip.back.domain.User;
import lombok.Builder;
import lombok.Getter;


import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attribute;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profile;
    private Provider provider;

    @Builder
    public OAuthAttributes(Map<String, Object> attribute, String nameAttributeKey, String name, String email, String profile) {
        this.attribute = attribute;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public static OAuthAttributes of(String provider, String userNameAttributeName, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(userNameAttributeName, attributes);
            default:
                throw new RuntimeException("500"); //@todo 예외 번호 수정 해야 함
        }
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attribute) {
        return OAuthAttributes.builder()
                .name((String) attribute.get("name"))
                .email((String) attribute.get("email"))
                .profile((String) attribute.get("picture"))
                .attribute(attribute)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .profile(profile)
                .provider(Provider.GOOGLE)
                .build();
    }
}
