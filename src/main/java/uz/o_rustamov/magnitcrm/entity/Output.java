package uz.o_rustamov.magnitcrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column
    private Date date;

    @Column(nullable = false)
    @JsonProperty("all_product_cost")
    private Long allProductCost;

    public Output(Date date, Long allProductCost, Long takenMoney, User manager, Recipient recipient, String note, boolean checkedByClient) {
        this.date = date;
        this.allProductCost = allProductCost;
        this.takenMoney = takenMoney;
        this.manager = manager;
        this.recipient = recipient;
        this.note = note;
        this.checkedByRecipient = checkedByClient;
    }

    @Column
    @JsonProperty("taken_money")
    private Long takenMoney;

    @ManyToOne
    private User manager;

    @ManyToOne
    private Recipient recipient;

    @Column
    private String note;


    @Column
    @JsonProperty("checked_by_recipient")
    boolean checkedByRecipient;


}
