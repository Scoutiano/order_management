package com.example.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime orderedAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ProductOrder> productOrders = new ArrayList<>();
    public void addProductOrder(ProductOrder productOrder) {
        productOrders.add(productOrder);
    }
}
