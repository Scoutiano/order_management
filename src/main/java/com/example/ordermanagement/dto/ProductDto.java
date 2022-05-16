package com.example.ordermanagement.dto;

import com.example.ordermanagement.model.Stock;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String slug;

    private String name;

    private String reference;

    private Float price;

    private Float vat;

    private Boolean stockable;
}
