package uz.o_rustamov.magnitcrm.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private User user;
}