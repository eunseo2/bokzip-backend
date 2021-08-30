package bokzip.back.repository;

import bokzip.back.domain.General;
import bokzip.back.domain.User;
import bokzip.back.dto.GeneralMapping;
import bokzip.back.dto.HomeMapping;
import bokzip.back.dto.ScrapMapping;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //DI
public interface GeneralRepository extends JpaRepository<General, Long> {
    List<GeneralMapping> findAllBy(Sort id);
}
