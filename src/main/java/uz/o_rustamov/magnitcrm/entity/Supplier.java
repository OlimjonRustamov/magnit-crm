package uz.o_rustamov.magnitcrm.entity;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}