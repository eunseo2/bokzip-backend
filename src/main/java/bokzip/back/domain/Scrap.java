package bokzip.back.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Scraps")
@Data
public class Scrap{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scrap_id")
    private Long id;

    /**
     * @see : scarp은 user_id와 post_id를 통해 users, posts를 조회할 수 있지만,
     *        posts, users에는 scarp_id가 없음 -> 단방향 관계(@ManyToOne)
     *        scrap(1) : users(n) / posts(n)
     */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
