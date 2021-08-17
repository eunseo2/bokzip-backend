package bokzip.back.domain;

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
}
