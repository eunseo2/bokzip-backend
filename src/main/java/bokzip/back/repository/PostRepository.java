package bokzip.back.repository;

import bokzip.back.domain.Post;
import bokzip.back.dto.PostMapping;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostMapping> findByCategoryLike(@Param("category") String category);
    List<PostMapping> findAllBy(Sort id);
    List<PostMapping> findByCategoryContainsAndAreaContainsOrderByViewCountDesc(String category, String area);
    List<PostMapping> findByCategoryContainsAndAreaContainsOrderByStarCountDesc(String category, String area);
    List<PostMapping> findByCategoryContainsAndAreaContainsOrderByIdAsc(String category, String area);

}
