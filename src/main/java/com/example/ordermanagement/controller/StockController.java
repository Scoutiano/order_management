package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.StockDto;
import com.example.ordermanagement.model.Stock;
import com.example.ordermanagement.service.StockService;
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
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    @ApiOperation(value = "Get All Stocks REST API")
    @GetMapping()
    public ResponseEntity<List<StockDto>> getAll() {
        List<Stock> stocks = stockService.getStocks();
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get One Stock By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getOne(@PathVariable Long id) {
        Stock stock = stockService.getOne(id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    @ApiOperation(value = "Get One Product's Stocks By ID REST API")
    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockDto>> getProductStocks(@PathVariable Long id) {
        List<Stock> stocks = stockService.getProductStocks(id);
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Create Product REST API")
    @PostMapping("/product/")
    public ResponseEntity<StockDto> create(@RequestBody StockDto stockDto) throws ParseException {
        Stock stock = stockService.create(stockDto);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    @ApiOperation(value = "Update One Product By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<StockDto> update(@RequestBody StockDto stockDto, @PathVariable Long id) throws ParseException {
        Stock stock = stockService.update(stockDto, id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    @ApiOperation(value = "Delete One Product By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.ok(id);
    }
}
