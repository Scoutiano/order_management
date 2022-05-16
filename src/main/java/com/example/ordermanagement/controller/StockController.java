package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.StockDto;
import com.example.ordermanagement.model.Stock;
import com.example.ordermanagement.service.StockService;
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

    /**
     * Path: {/api/v1/stock}
     * Method: GET
     *
     * Get a list of all stocks
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<StockDto>> getAll() {
        List<Stock> stocks = stockService.getStocks();
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Path: {/api/v1/stock/#id}
     * Method: GET
     *
     * Get specific stock by id
     *
     * @param id: id of specified stock
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getOne(@PathVariable Long id) {
        Stock stock = stockService.getOne(id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Path: {/api/v1/stock/product/#id}
     * Method: GET
     *
     * Get list of stocks for a product
     *
     * @param id: id of specified product
     * @return
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockDto>> getProductStocks(@PathVariable Long id) {
        List<Stock> stocks = stockService.getProductStocks(id);
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Path: {/api/v1/stock/product/}
     * Method: POST
     *
     * Create a stock with specified information
     *
     * @param stockDto: stock data to be created
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PostMapping("/product/")
    public ResponseEntity<StockDto> create(@RequestBody StockDto stockDto) throws ParseException {
        Stock stock = stockService.create(stockDto);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Path: {/api/v1/stock/#id}
     * Method: PUT
     *
     * Update a stock with specified information
     *
     * @param stockDto: stock data to be updated
     * @param id: id of stock to be updated
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PutMapping("/{id}")
    public ResponseEntity<StockDto> update(@RequestBody StockDto stockDto, @PathVariable Long id) throws ParseException {
        Stock stock = stockService.update(stockDto, id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Path: {/api/v1/stock/#id}
     * Method: PUT
     *
     * Delete a stock with specified id
     *
     * @param id: id of stock to be deleted
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.ok(id);
    }
}
