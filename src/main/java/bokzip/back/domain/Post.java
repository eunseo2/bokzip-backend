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
    @Column(name="post_id")
    private Long id;

    @Column()
    private String title;

    //썸네일
    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    //내용
    @Column(columnDefinition = "TEXT")
    private String description;

    //지원대상
    @Column(columnDefinition = "TEXT")
    private String target;

    //선정기준
    @Column(columnDefinition = "TEXT",nullable = true)
    private String criteria;

    //분야 or 지역
    @Column()
    private String category;

    //연락처
    @Column(columnDefinition = "TEXT")
    private String contact;

    //신청주소
    @Column(columnDefinition = "TEXT",name = "apply_url")
    private String applyUrl;


    @Column(columnDefinition = "TEXT", name = "how_to_apply")
    private String howToApply;

    //조회수
    @Column(name = "view_count",columnDefinition = "integer default 0")
    private Integer viewCount;

    //스크랩 수
    @Column(name = "star_count",columnDefinition = "integer default 0")
    private Integer starCount;

}