package y88.kirill.msauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import y88.kirill.msauth.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    boolean existsUserByLogin(String login);
    boolean existsUserByEmail(String email);

}
