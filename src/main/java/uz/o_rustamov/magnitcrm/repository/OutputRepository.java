package uz.o_rustamov.magnitcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.o_rustamov.magnitcrm.entity.Output;

import java.sql.Date;
import java.util.List;

@Repository
public interface OutputRepository extends JpaRepository<Output, Long> {

    @Query(value = "select * from output where date between :from and :to order by date asc", nativeQuery = true)
    List<Output> findAllByPeriod(Date from, Date to);

    @Query(value = "select * from output where date between :from and :to and recipient_id=:recipientId order by date asc", nativeQuery = true)
    List<Output> findAllByPeriodAndRecipientId(Date from, Date to, Long recipientId);

    @Query(value = "select * from output order by date desc", nativeQuery = true)
    List<Output> findAllDesc();

    @Query(value = "select sum(taken_money) from output", nativeQuery = true)
    Long sumTakenMoney();

    @Query(value = "select sum(taken_money) from output where date between :from and :to", nativeQuery = true)
    Long sumTakenMoney(Date from, Date to);

    @Query(value = "select sum(taken_money) from output where date between :from and :to and recipient_id=:recipientId", nativeQuery = true)
    Long sumTakenMoney(Date from, Date to, Long recipientId);

    @Query(value = "select sum(taken_money) from output where recipient_id=:recipientId", nativeQuery = true)
    Long sumTakenMoney(Long recipientId);

    @Query(value = "select sum(all_product_cost) from output", nativeQuery = true)
    Long sumAllProductsCost();

    @Query(value = "select sum(all_product_cost) from output where date between :from and :to", nativeQuery = true)
    Long sumAllProductsCost(Date from, Date to);

    @Query(value = "select sum(all_product_cost) from output where date between :from and :to and recipient_id=:recipientId", nativeQuery = true)
    Long sumAllProductsCost(Date from, Date to, Long recipientId);

    @Query(value = "select sum(all_product_cost) from output where recipient_id=:recipientId", nativeQuery = true)
    Long sumAllProductsCost(Long recipientId);

    List<Output> findAllByRecipient_Id(Long recipient_id);

    Long countByRecipient_Id(Long recipient_id);

    @Query(value = "select count(*) from output where date between :from and :to", nativeQuery = true)
    Long countByPeriod(Date from, Date to);

    @Query(value = "select count(*) from output where date between :from and :to and recipient_id=:recipientId", nativeQuery = true)
    Long countByPeriodAndRecipient(Date from, Date to, Long recipientId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE output SET checked_by_recipient=true WHERE id=:outputId", nativeQuery = true)
    void confirmOutput(Long outputId);


}
