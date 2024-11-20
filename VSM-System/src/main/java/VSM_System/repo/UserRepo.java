package VSM_System.repo;

import VSM_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
