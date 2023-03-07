package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.magnitcrm.entity.Input;

import java.sql.Date;
import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {

    @Query(value = "select * from input where date between :from and :to order by date asc", nativeQuery = true)
    List<Input> findAllByPeriod(Date from, Date to, Pageable pageable);

    @Query(value = "select * from input where date between :from and :to and supplier_id=:supplierId order by date asc", nativeQuery = true)
    List<Input> findAllByPeriodAndSupplierId(Date from, Date to, Long supplierId, Pageable pageable);

    @Query(value = "select * from input order by date desc", nativeQuery = true)
    List<Input> findAllDesc(Pageable pageable);

    @Query(value = "select sum(given_money) from input", nativeQuery = true)
    Long sumGivenMoney();

    @Query(value = "select sum(given_money) from input where date between :from and :to", nativeQuery = true)
    Long sumGivenMoney(Date from, Date to);

    @Query(value = "select sum(given_money) from input where date between :from and :to and supplier_id=:supplierId", nativeQuery = true)
    Long sumGivenMoney(Date from, Date to, Long supplierId);

    @Query(value = "select sum(given_money) from input where supplier_id=:supplierId", nativeQuery = true)
    Long sumGivenMoney(Long supplierId);

    @Query(value = "select sum(all_product_cost) from input", nativeQuery = true)
    Long sumAllProductsCost();

    @Query(value = "select sum(all_product_cost) from input where date between :from and :to", nativeQuery = true)
    Long sumAllProductsCost(Date from, Date to);

    @Query(value = "select sum(all_product_cost) from input where date between :from and :to and supplier_id=:supplierId", nativeQuery = true)
    Long sumAllProductsCost(Date from, Date to, Long supplierId);

    @Query(value = "select sum(all_product_cost) from input where supplier_id=:supplierId", nativeQuery = true)
    Long sumAllProductsCost(Long supplierId);

    List<Input> findAllBySupplier_Id(Long supplier_id, Pageable pageable);

    @Query(value = "select count(*) from input where date between :from and :to", nativeQuery = true)
    Long countByPeriod(Date from, Date to);

    @Query(value = "select count(*) from input where date between :from and :to and supplier_id=:supplierId", nativeQuery = true)
    Long countByPeriodAndSupplierId(Date from, Date to, Long supplierId);

    Long countBySupplier_Id(Long supplier_id);


}
