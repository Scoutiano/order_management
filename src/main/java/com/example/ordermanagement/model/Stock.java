package com.example.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    private Long id;

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Integer quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
}
