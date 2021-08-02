package bokzip.back.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Personal_info")
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @brief : PK, auto_increment
    private Long pid; // @param : personal_info id

    @OneToMany(mappedBy = "uid") // 1:n //@TODO : users 테이블 기준으로 1:n
//    @JoinColumn(name = "uid_fk")  //오류나서 주석처리
    private List<User> uid; // @brief : users 테이블의 uid (fk)

    @Column(length = 64)
    private String situation; // @param : 상황(장애, 임신, 한부모 등)에 해당하는 변수 // @TODO : 변수명 변경 필요

    // @TODO : 거주지 추가

    @Column(length = 128)
    private String topic; // @param : 관심주제

    //@brief : 필드의 getter, setter
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<User> getUid() {
        return uid;
    }

    public void setUid(List<User> uid) {
        this.uid = uid;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
