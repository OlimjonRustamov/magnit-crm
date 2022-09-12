package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.o_rustamov.magnitcrm.entity.OutputProduct;

import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Long> {

    @Transactional
    @Modifying
    void deleteAllByOutput_Id(Long output_id);

    List<OutputProduct> findAllByOutput_Id(Long output_id);

}
