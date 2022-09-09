package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.magnitcrm.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    @Query(value = "select fcm_token from users where role_id=2", nativeQuery = true)
    List<String> findManagersFcmTokens();
}
