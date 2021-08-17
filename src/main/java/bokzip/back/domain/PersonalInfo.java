//package bokzip.back.domain;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
// @TODO : 해당 코드는 나중에 확실하게 사용안하는 것으로 확인되면 삭제하겠습니다.
//
//@Entity
//@Data
//@Table(name = "Personal_info")
//public class PersonalInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long personal_id;
//
//    @OneToMany(mappedBy = "uid") // 1:n //@TODO : users 테이블 기준으로 1:n
//    private List<User> user_id;
//
//    //거주지 정보
//    @Column
//    private String address;
//
//    //관심주제
//    @Column(length = 128)
//    private String topic;
//}
