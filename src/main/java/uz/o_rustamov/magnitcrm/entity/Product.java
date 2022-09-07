package uz.o_rustamov.magnitcrm.entity;

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

    @CreationTimestamp
    Timestamp createdAt;

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
    private int costForDriver;

    @Column(nullable = false)
    private int costForClient;

    @Column(nullable = false)
    private int receivedCost;

}
