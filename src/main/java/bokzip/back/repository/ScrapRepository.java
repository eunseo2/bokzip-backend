package bokzip.back.repository;

import bokzip.back.domain.General;
import bokzip.back.domain.Post;
import bokzip.back.domain.Scrap;
import bokzip.back.domain.User;
import bokzip.back.dto.ScrapMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByUserAndPost(User user, Post post);
    Optional<Scrap> findByUserAndGeneral(User user, General general);
    List<ScrapMapping> findByUser(User user);
}
