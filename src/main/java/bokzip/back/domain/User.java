package bokzip.back.domain;

import bokzip.back.dto.Provider;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    // 소셜 로그인 email
    @Column(nullable = false)
    private String email;

    //소셜로그인 이름
    @Column(nullable = false)
    private String name;

    //소셜로그인 프로필사진
    @Column(nullable = false)
    private String profile;

    //거주지 정보
    @Column
    private String address;

    //관심주제
    @Column(length = 128)
    private String category;

    // @see : 구글, 카카오 소셜로그인을 위해 생성
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    /**
     * @see : No default constructor for entity 에러 해결을 위해 기본 생성자 생성
     *        (JPA 2.0 표준 스펙에 반드시 기본 생성자가 있어야 한다고 함)
     */
    public User(){
    }

    @Builder
    public User(String email, String name, String profile, Provider provider){
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.provider = provider;
    }

    public User update(String name, String profile){
        this.name = name;
        this.profile = profile;
        return this;
    }

    public String getRoleKey(){
        return this.provider.getKey();
    }
    public String getRoleTitle(){ return this.provider.getTitle(); }
}
