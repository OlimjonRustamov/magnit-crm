package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.magnitcrm.entity.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    boolean existsByName(String name);
}
