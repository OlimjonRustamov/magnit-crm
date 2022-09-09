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
public class Input {

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

    @ManyToOne
    private Supplier supplier;

    @Column(nullable = false)
    @JsonProperty("given_money")
    private Long givenMoney;

    @ManyToOne
    private User manager;

    @Column
    private String note;

    public Input(Date date, Long allProductCost, Supplier supplier, Long givenMoney, User manager, String note) {
        this.date = date;
        this.allProductCost = allProductCost;
        this.supplier = supplier;
        this.givenMoney = givenMoney;
        this.manager = manager;
        this.note = note;
    }
}
