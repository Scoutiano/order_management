package com.example.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    private Float price;

    private Float vat;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;
}
