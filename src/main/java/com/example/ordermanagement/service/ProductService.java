package com.example.ordermanagement.service;

import com.example.ordermanagement.controller.exception.AssetNotFoundException;
import com.example.ordermanagement.dto.ProductDto;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.Entity;
import com.example.ordermanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> (new AssetNotFoundException(Entity.PRODUCT)));
    }

    public Product create(ProductDto productDto) throws ParseException {
        Product product = convertToEntity(productDto);
        return productRepository.save(product);
    }

    public Product update(ProductDto productDto, Long id) throws ParseException {
        Product product = convertToEntity(productDto);
        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }

    /**
     * Utility method to convert Product to ProductDTO
     *
     * @param product: Product to be converted
     * @return
     */
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }
}
