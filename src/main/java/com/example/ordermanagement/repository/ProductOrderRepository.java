package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
//    List<ProductOrder> findProductOrderByProduct(Product product);
//    List<ProductOrder> findProductOrderByOrder(Order order);
}
