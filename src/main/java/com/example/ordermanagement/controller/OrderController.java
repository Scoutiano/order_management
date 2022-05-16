package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.ProductOrderService;
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

    /**
     * Path: {/api/v1/order}
     * Method: GET
     *
     * Get a list of all orders
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders.stream()
                .map(orderService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Path: {/api/v1/order/#id}
     * Method: GET
     *
     * Get specific order by id
     *
     * @param id: id of specified order
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOne(@PathVariable Long id) throws ParseException {
        Order order = orderService.getOne(id);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    /**
     * Path: {/api/v1/order/}
     * Method: POST
     *
     * Create a order with specified information
     *
     * @param orderDto: order data to be created
     * @return
     */
    @PostMapping()
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) throws ParseException {
        Order order = orderService.create(orderDto);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    /**
     * Path: {/api/v1/order/#id}
     * Method: PUT
     *
     * Update a order with specified information
     *
     * @param orderDto: order data to be updated
     * @param id: id of order to be updated
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto, @PathVariable Long id) throws ParseException {
        Order order = orderService.update(orderDto, id);
        return ResponseEntity.ok(orderService.convertToDto(order));
    }

    /**
     * Path: {/api/v1/order/#id}
     * Method: PUT
     *
     * Delete a order with specified id
     *
     * @param id: id of order to be deleted
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok(id);
    }


}
