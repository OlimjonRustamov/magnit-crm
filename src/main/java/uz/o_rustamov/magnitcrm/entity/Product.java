package uz.o_rustamov.magnitcrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreationTimestamp
    Timestamp createdAt;

    @JsonIgnore
    @UpdateTimestamp
    Timestamp updatedAt;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int balance;

    @ManyToOne
    private Supplier supplier;

    @Column(nullable = false)
    private int countInBox;

    @Column(nullable = false)
    @JsonProperty("cost_for_driver")
    private int costForDriver;

    @JsonProperty("cost_for_client")
    @Column(nullable = false)
    private int costForClient;

    @JsonProperty("received_cost")
    @Column(nullable = false)
    private int receivedCost;

}
