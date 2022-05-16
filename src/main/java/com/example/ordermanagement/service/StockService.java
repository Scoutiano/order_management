package com.example.ordermanagement.service;

import com.example.ordermanagement.controller.exception.AssetNotFoundException;
import com.example.ordermanagement.dto.StockDto;
import com.example.ordermanagement.dto.StockDto;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.Stock;
import com.example.ordermanagement.model.Stock;
import com.example.ordermanagement.model.Entity;
import com.example.ordermanagement.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public List<Stock> getProductStocks(Long id) {
        Product product = new Product();
        product.setId(id);
        return stockRepository.findStockByProduct(product);
    }

    public Stock getOne(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> (new AssetNotFoundException(Entity.STOCK)));
    }

    public Stock create(StockDto stockDto) throws ParseException {
        Stock stock = convertToEntity(stockDto);
        return stockRepository.save(stock);
    }

    public Stock update(StockDto stockDto, Long id) throws ParseException {
        Stock stock = convertToEntity(stockDto);
        stock.setId(id);
        return stockRepository.save(stock);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    public StockDto getCurrentStock(Long productId) {
        Product product = new Product();
        product.setId(productId);
        List<Stock> productStocks = stockRepository.findStockByProduct(product);
        if(productStocks.size() == 0) {
            throw new AssetNotFoundException(Entity.STOCK);
        }
        Stock current = productStocks.get(0);
        for(Stock stock: productStocks) {
            if(stock.getUpdatedAt().isBefore(current.getUpdatedAt())) {
                current = stock;
            }
        }

        StockDto stockDto = convertToDto(current);
        return stockDto;
    }

    public Stock convertToEntity(StockDto stockDto) {
        Stock stock = modelMapper.map(stockDto, Stock.class);
        Product product = new Product();
        product.setId(stockDto.getProductId());
        stock.setProduct(product);
        return stock;
    }

    /**
     * Utility method to convert Stock to StockDTO
     *
     * @param stock: Stock to be converted
     * @return
     */
    public StockDto convertToDto(Stock stock) {
        StockDto stockDto = modelMapper.map(stock, StockDto.class);
        stockDto.setProductId(stock.getProduct().getId());
        return stockDto;
    }
}
