package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findStockByProduct(Product product);
}
