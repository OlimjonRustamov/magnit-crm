package uz.o_rustamov.magnitcrm.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class OutputProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @CreationTimestamp
    @JsonIgnore
    Timestamp createdAt;

    @UpdateTimestamp
    @JsonIgnore
    Timestamp updatedAt;

    @Column
    @JsonProperty("box_count")
    private int boxCount;

    @ManyToOne
    @JsonIgnore
    private Output output;

    @Column
    @JsonProperty("cost_pcs")
    private int costPcs;

    public OutputProduct(Product product, int boxCount, Output output, int costPcs) {
        this.product = product;
        this.boxCount = boxCount;
        this.output = output;
        this.costPcs = costPcs;
    }
}