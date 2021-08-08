package bokzip.back.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Posts")
@Data

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    //썸네일
    @Column(length = 255)
    private String thumbnail;

    //내용
    @Column(columnDefinition = "TEXT")
    private String description;

    //지원대상
    @Column(columnDefinition = "TEXT")
    private String target;

    //선정기준
    @Column(columnDefinition = "TEXT")
    private String criteria;

    //분야 or 지역
    @Column
    private String category;

    //연락처
    @Column
    private String contact;

    //신청주소
    @Column(name = "apply_url")
    private String applyUrl;


    @Column(name = "how_to_apply")
    private String howToApply;

    //조회수
    @Column(name = "view_count",columnDefinition = "integer default 0")
    private Integer viewCount;

    //스크랩 수
    @Column(name = "star_count",columnDefinition = "integer default 0")
    private Integer starCount;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}