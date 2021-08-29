package bokzip.back.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Generals")
@Data
public class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="general_id")
    private Long id;

    //7가지 분야(건강/의료, 문화, 보육 등)
    @Column()
    private String category;

    //제목
    @Column()
    private String title;

    //내용
    @Column(columnDefinition = "TEXT")
    private String description;

    //이미지
    @Column(columnDefinition = "TEXT")
    private String image;

    //스크랩 수
    @Column(name = "star_count",columnDefinition = "integer default 0")
    private Integer starCount;
}
