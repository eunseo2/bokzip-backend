package bokzip.back.repository;

import bokzip.back.domain.Post;
import bokzip.back.dto.HomeMapping;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //DI
public interface PostRepository extends JpaRepository<Post, Long> {

    // @param : 중앙부처 상황별 데이터 조회

    /**
     * @todo
     * @see : 우선 start_count를 보내도록 했음. user와 scrap 디비에 값을 넣은 후에는 join 결과를 보내도록 수정해야 할 듯
     * 유저가 해당 아이템을 스크랩 했는지의 여부니까!
     * 참고사이트 : https://velog.io/@devyu/spring-Spring-Data-JPA-%EA%B8%B0%EB%B3%B8%EC%A0%95%EB%A6%AC
     * https://ppomelo.tistory.com/155?category=908484
     */

    List<PostMapping> findByCategoryLike(@Param("category") String category);

    List<PostMapping> findAllBy(Sort id);


    List<PostMapping> findByCategoryContainsOrderByViewCountDesc(String category);

    List<PostMapping> findByCategoryContainsOrderByStarCountDesc(String category);
}
