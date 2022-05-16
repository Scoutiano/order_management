package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.ProductDto;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    /**
     * Path: {/api/v1/product}
     * Method: GET
     *
     * Get a list of all products
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream()
                .map(productService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Path: {/api/v1/product/#id}
     * Method: GET
     *
     * Get specific product by id
     *
     * @param id: id of specified product
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable Long id) {
        Product product = productService.getOne(id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Path: {/api/v1/product/}
     * Method: POST
     *
     * Create a product with specified information
     *
     * @param productDto: product data to be created
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PostMapping()
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) throws ParseException {
        Product product = productService.create(productDto);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Path: {/api/v1/product/#id}
     * Method: PUT
     *
     * Update a product with specified information
     *
     * @param productDto: product data to be updated
     * @param id: id of product to be updated
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable Long id) throws ParseException {
        Product product = productService.update(productDto, id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Path: {/api/v1/product/#id}
     * Method: PUT
     *
     * Delete a product with specified id
     *
     * @param id: id of product to be deleted
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(id);
    }
}
