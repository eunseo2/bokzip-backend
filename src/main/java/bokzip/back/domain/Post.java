package bokzip.back.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Posts")
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
    @Column(length = 255)
    private String description;

    @Column
    private String age;

    @Column
    private String category;

    //조회수
    @Column(name = "view_count",columnDefinition = "integer default 0")
    private Integer viewCount;

    //스크랩 수
    @Column(name = "star_count",columnDefinition = "integer default 0")
    private Integer starCount;

    //신청주소
    @Column(name = "apply_url")
    private String applyUrl;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
}