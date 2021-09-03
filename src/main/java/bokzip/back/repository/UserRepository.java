package bokzip.back.repository;

import bokzip.back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //@param : OAuth2.0 인증 시 email 정보를 가져오기 위한
    Optional<User> findByEmail(String email);
}
