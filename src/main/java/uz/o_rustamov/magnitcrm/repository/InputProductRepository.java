package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.o_rustamov.magnitcrm.entity.InputProduct;

import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Long> {
    @Transactional
    @Modifying
    void deleteAllByInput_Id(Long input_id);

    List<InputProduct> findAllByInput_Id(Long input_id);
}
