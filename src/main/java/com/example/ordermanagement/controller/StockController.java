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

    /**
     * Get a list of all stocks from REST API
     *
     * @return List of stocks
     */
    @ApiOperation(value = "Get All Stocks REST API")
    @GetMapping()
    public ResponseEntity<List<StockDto>> getAll() {
        List<Stock> stocks = stockService.getStocks();
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Get a single stock by their ID
     *
     * @param id Id of stock to be retrieved
     * @return stock with provided Id
     */
    @ApiOperation(value = "Get One Stock By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getOne(@PathVariable Long id) {
        Stock stock = stockService.getOne(id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Get a set of stocks for a product by product id
     *
     * @param id Id of product whose stocks are to be retrieved
     * @return set of stocks with provided product Id
     */
    @ApiOperation(value = "Get One Product's Stocks By ID REST API")
    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockDto>> getProductStocks(@PathVariable Long id) {
        List<Stock> stocks = stockService.getProductStocks(id);
        return ResponseEntity.ok(stocks.stream()
                .map(stockService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Create an stock
     *
     * @param stockDto information of stock to be created
     * @return verify creation by returning created stock information
     * @throws ParseException Date formatting error
     */
    @ApiOperation(value = "Create Product REST API")
    @PostMapping("/product/")
    public ResponseEntity<StockDto> create(@RequestBody StockDto stockDto) throws ParseException {
        Stock stock = stockService.create(stockDto);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Update an existing stock information
     *
     * @param stockDto updated order information
     * @param id id of stock to be updated
     * @return verify the updating of stock
     * @throws ParseException
     */
    @ApiOperation(value = "Update One Product By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<StockDto> update(@RequestBody StockDto stockDto, @PathVariable Long id) throws ParseException {
        Stock stock = stockService.update(stockDto, id);
        return ResponseEntity.ok(stockService.convertToDto(stock));
    }

    /**
     * Delete a stock by Id
     *
     * @param id id of stock to be deleted
     * @return returns id of deleted stock
     */
    @ApiOperation(value = "Delete One Product By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.ok(id);
    }
}
