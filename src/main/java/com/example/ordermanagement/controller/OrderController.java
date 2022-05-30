package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.ProductOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    private final ProductOrderService productOrderService;

    @ApiOperation(value = "Get All Orders REST API")
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders.stream()
                .map(orderService::convertToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get One Order By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOne(@PathVariable Long id) throws ParseException {
        Order order = orderService.getOne(id);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    @ApiOperation(value = "Create Order REST API")
    @PostMapping()
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) throws ParseException {
        Order order = orderService.create(orderDto);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    @ApiOperation(value = "Update Order By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto, @PathVariable Long id) throws ParseException {
        Order order = orderService.update(orderDto, id);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    @ApiOperation(value = "Delete Order By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok(id);
    }


}
