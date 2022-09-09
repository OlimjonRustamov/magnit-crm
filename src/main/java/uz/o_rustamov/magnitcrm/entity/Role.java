package uz.o_rustamov.magnitcrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.o_rustamov.magnitcrm.enums.Permission;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(nullable = false, unique = true)
    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private List<Permission> permissionList;
}
