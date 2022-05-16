package com.example.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String slug;

    private String name;

    private String reference;

    private Float price;

    private Float vat;

    private Boolean stockable;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonManagedReference
    private List<Stock> stocks = new ArrayList<>();
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonManagedReference
    private List<ProductOrder> productOrders = new ArrayList<>();
    public void addProductOrder(ProductOrder productOrder) {
        productOrders.add(productOrder);
    }
}
