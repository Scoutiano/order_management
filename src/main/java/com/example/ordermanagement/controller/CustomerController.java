package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.CustomerDto;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
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
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Path: {/api/v1/customer}
     * Method: GET
     *
     * Get a list of all customers
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers.stream()
                .map(customerService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Path: {/api/v1/customer/#id}
     * Method: GET
     *
     * Get specific customer by id
     *
     * @param id: id of specified customer
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        Customer customer = customerService.getOne(id);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Path: {/api/v1/customer/}
     * Method: POST
     *
     * Create a customer with specified information
     *
     * @param customerDto: customer data to be created
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PostMapping()
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) throws ParseException {
        Customer customer = customerService.create(customerDto);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Path: {/api/v1/customer/#id}
     * Method: PUT
     *
     * Update a customer with specified information
     *
     * @param customerDto: customer data to be updated
     * @param id: id of customer to be updated
     * @return
     * @throws ParseException: Exception for the received date format
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto, @PathVariable Long id) throws ParseException {
        Customer customer = customerService.update(customerDto, id);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Path: {/api/v1/customer/#id}
     * Method: PUT
     *
     * Delete a customer with specified id
     *
     * @param id: id of customer to be deleted
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(id);
    }

}
