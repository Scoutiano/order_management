package com.example.ordermanagement.service;

import com.example.ordermanagement.controller.exception.BadRequestException;
import com.example.ordermanagement.dto.ProductOrderDto;
import com.example.ordermanagement.dto.StockDto;
import com.example.ordermanagement.model.Entity;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.ProductOrder;
import com.example.ordermanagement.model.Stock;
import com.example.ordermanagement.repository.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductOrderService {

    @Autowired
    private final ModelMapper modelMapper;

    private final ProductService productService;

    private final StockService stockService;

    private final ProductOrderRepository productOrderRepository;

    public ProductOrder convertToEntity(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = modelMapper.map(productOrderDto, ProductOrder.class);
        Product product = new Product();
        product.setId(productOrderDto.getProductId());
        productOrder.setProduct(product);

        Float price = productService.getOne(productOrderDto.getProductId()).getPrice();
        productOrder.setPrice(productOrder.getQuantity() * price);
        return productOrder;
    }

    public ProductOrderDto convertToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = modelMapper.map(productOrder, ProductOrderDto.class);
        productOrderDto.setProductId(productOrder.getProduct().getId());
        return productOrderDto;
    }

    @Transactional
    public void create(List<ProductOrder> productOrders) throws ParseException {
        for(ProductOrder productOrder:productOrders) {
            StockDto stockDto = stockService.getCurrentStock(productOrder.getProduct().getId());
            if(stockDto.getQuantity() < productOrder.getQuantity()) {
                throw new BadRequestException("Not enough of product with id " + productOrder.getProduct().getId() + " to complete this order.", Entity.PRODUCTORDER, "Out of stock");
            }
            stockDto.setQuantity(stockDto.getQuantity() - productOrder.getQuantity());
            stockService.update(stockDto, stockDto.getId());
        }
    }

    public void update(List<ProductOrder> productOrders) throws ParseException {
        for(ProductOrder productOrder:productOrders) {
            StockDto stockDto = stockService.getCurrentStock(productOrder.getProduct().getId());
            Long previousQuantity = productOrderRepository.findById(productOrder.getId()).get().getId();
            if(stockDto.getQuantity()+previousQuantity < productOrder.getQuantity()) {
                throw new BadRequestException("Not enough of product with id " + productOrder.getProduct().getId() + " to complete this order.", Entity.PRODUCTORDER, "Out of stock");
            }
            stockDto.setQuantity(stockDto.getQuantity() - productOrder.getQuantity());
            stockService.update(stockDto, stockDto.getId());
        }
    }
}
