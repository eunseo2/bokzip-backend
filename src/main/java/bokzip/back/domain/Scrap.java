package bokzip.back.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Scraps")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    /**
     * @see : scarp은 user_id와 post_id를 통해 users, posts를 조회할 수 있지만,
     * posts, users에는 scarp_id가 없음 -> 단방향 관계(@ManyToOne)
     * scrap(1) : users(n) / posts(n)
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_id")
    private General general;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void updateGeneral(User user, General general) {
        this.user = user;
        this.general = general;
    }

    public void updatePost(User user, Post post) {
        this.user = user;
        this.post = post;
    }


}
