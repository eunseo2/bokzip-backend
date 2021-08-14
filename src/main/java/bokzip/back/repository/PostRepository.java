package bokzip.back.repository;

import bokzip.back.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //DI
public interface PostRepository extends JpaRepository<Post, Long> {

    // @param : 중앙부처 상황별 데이터 조회
    List<Post> findByCategory(@Param("category") String category);

    // @param : PK 값으로 READ
    @Override
    Optional<Post> findById(Long id);
}
