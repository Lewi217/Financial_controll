package DevLewi.server.repositories;

import DevLewi.server.model.User;  // Import the User entity
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
