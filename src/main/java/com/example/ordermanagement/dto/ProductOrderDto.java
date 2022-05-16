package com.example.ordermanagement.dto;

import com.example.ordermanagement.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {

    private Long id;

    private Integer quantity;

    private Float price;

    private Float vat;

    private Long productId;
}
