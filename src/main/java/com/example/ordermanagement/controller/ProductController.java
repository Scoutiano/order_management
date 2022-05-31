package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.ProductDto;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import io.swagger.annotations.ApiOperation;
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
     * Get a list of all products from REST API
     *
     * @return List of products
     */
    @ApiOperation(value = "Get All Products REST API")
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream()
                .map(productService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Get a single product by their ID
     *
     * @param id Id of product to be retrieved
     * @return product with provided Id
     */
    @ApiOperation(value = "Get One Product By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable Long id) {
        Product product = productService.getOne(id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Create an product
     *
     * @param productDto information of product to be created
     * @return verify creation by returning created product information
     * @throws ParseException Date formatting error
     */
    @ApiOperation(value = "Create order REST API")
    @PostMapping()
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) throws ParseException {
        Product product = productService.create(productDto);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Update an existing product information
     *
     * @param productDto updated order information
     * @param id id of product to be updated
     * @return verify the updating of product
     * @throws ParseException
     */
    @ApiOperation(value = "Update Product By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable Long id) throws ParseException {
        Product product = productService.update(productDto, id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    /**
     * Delete a product by Id
     *
     * @param id id of order to be deleted
     * @return returns id of deleted order
     */
    @ApiOperation(value = "Delete One Product By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(id);
    }
}
