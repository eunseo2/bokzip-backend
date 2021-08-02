package bokzip.back.domain;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @brief : PK, auto_increment
    private Long uid; // @param : user id

    @OneToOne // 1:1
    @JoinColumn(name = "pid_fk") // @param : personal_info id (fk)
    private PersonalInfo pid; //

    // @brief : @Column 매핑을 생략하면 필드명을 사용해서 매핑. 필드명과 데이터필드명을 동일하게 설정하여 명시적 매핑 X

    @Column(nullable = false)
    private String email; // @param : 소셜 로그인 email

    @Column(nullable = false)
    private String passwd; // @param : 소셜 로그인 pw // @TODO : OAuth 인증 db 찾아보고 수정 필요 없애던가 말던가

    @Column(nullable = false)
    private String name; // @param : 소셜로그인 이름

    @Column(nullable = false)
    private String profile; // @param : 소셜로그인 프로필사진

    private String access_token; // @param : JWT 액세스 토큰 // @TODO : 회의 후 db에 보관할지(서버사이드), 세션에 저장할지

    private String refresh_token; // @param : JWT 리프레시 토큰  // @TODO : 회의 후 db에 보관할지(서버사이드), 세션에 저장할지

    // @brief : 각 필드의 getter, setter
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public PersonalInfo getPid() {
        return pid;
    }

    public void setPid(PersonalInfo pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
