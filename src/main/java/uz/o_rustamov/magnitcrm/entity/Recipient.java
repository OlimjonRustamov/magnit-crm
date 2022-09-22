package uz.o_rustamov.magnitcrm.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private User user;

    public Recipient(String name) {
        this.name = name;
    }

    public Recipient(String name, User user) {
        this.name = name;
        this.user = user;
    }
}