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

    @ApiOperation(value = "Get All Products REST API")
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products.stream()
                .map(productService::convertToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get One Product By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable Long id) {
        Product product = productService.getOne(id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    @ApiOperation(value = "Get All Orders REST API")
    @PostMapping()
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) throws ParseException {
        Product product = productService.create(productDto);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    @ApiOperation(value = "Update Product By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable Long id) throws ParseException {
        Product product = productService.update(productDto, id);
        return ResponseEntity.ok(productService.convertToDto(product));
    }

    @ApiOperation(value = "Delete One Product By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(id);
    }
}
