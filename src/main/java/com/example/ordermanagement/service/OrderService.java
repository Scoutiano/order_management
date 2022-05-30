package com.example.ordermanagement.service;

import com.example.ordermanagement.controller.exception.AssetNotFoundException;
import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.dto.ProductOrderDto;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.Entity;
import com.example.ordermanagement.model.ProductOrder;
import com.example.ordermanagement.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductOrderService productOrderService;

    @Autowired
    private final ModelMapper modelMapper;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOne(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> (new AssetNotFoundException(Entity.ORDER)));
    }

    @Transactional
    public Order create(OrderDto orderDto) throws ParseException {
        Order order = convertToEntity(orderDto);
        productOrderService.create(order.getProductOrders());
        return orderRepository.save(order);
    }

    @Transactional
    public Order update(OrderDto orderDto, Long id) throws ParseException {
        Order order = convertToEntity(orderDto);
        order.setId(id);
        productOrderService.update(order.getProductOrders());
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order convertToEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        order.setProductOrders(orderDto.getProductOrderDtos().stream()
                .map(productOrderService::convertToEntity)
                .collect(Collectors.toList()));
        return order;
    }

    /**
     * Utility method to convert Order to OrderDTO
     *
     * @param order: Order to be converted
     * @return
     */
    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setProductOrderDtos(order.getProductOrders().stream()
                .map(productOrderService::convertToDto)
                .collect(Collectors.toList()));
        return orderDto;
    }
}
