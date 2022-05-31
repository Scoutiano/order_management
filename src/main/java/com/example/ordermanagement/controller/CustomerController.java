package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.CustomerDto;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
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
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Get a list of all customers from REST API
     *
     * @return List of customers
     */
    @ApiOperation(value = "Get All Customers REST API")
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers.stream()
                .map(customerService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Get a single customer by their ID
     *
     * @param id Id of customer to be retrieved
     * @return customer with provided Id
     */
    @ApiOperation(value = "Get One Customer By ID REST API")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        Customer customer = customerService.getOne(id);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Create a customer (Deprecated, use registerUser in AuthController)
     *
     * @param customerDto information of customer to be created
     * @return verify creation by returning created customer information
     * @throws ParseException Date formatting in the recieved customerDto.getBornAt()
     */
    @Deprecated
    @ApiOperation(value = "Create Customer REST API")
    @PostMapping()
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) throws ParseException {
        Customer customer = customerService.create(customerDto);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Update an existing customer information
     *
     * @param customerDto updated customer information
     * @param id id of customer to be updated
     * @return verify the updating of customer
     * @throws ParseException
     */
    @ApiOperation(value = "Update One Customer By ID REST API")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto, @PathVariable Long id) throws ParseException {
        Customer customer = customerService.update(customerDto, id);
        return ResponseEntity.ok(customerService.convertToDto(customer));
    }

    /**
     * Delete a customer by Id
     *
     * @param id id of customer to be deleted
     * @return returns id of deleted customer
     */
    @ApiOperation(value = "Delete One Customer By ID REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(id);
    }
}
