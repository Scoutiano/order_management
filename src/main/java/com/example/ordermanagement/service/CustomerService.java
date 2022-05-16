package com.example.ordermanagement.service;

import com.example.ordermanagement.controller.exception.AssetNotFoundException;
import com.example.ordermanagement.dto.CustomerDto;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Entity;
import com.example.ordermanagement.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOne(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> (new AssetNotFoundException(Entity.CUSTOMER)));
    }

    public Customer create(CustomerDto customerDto) throws ParseException {
        Customer customer = convertToEntity(customerDto);
        return customerRepository.save(customer);
    }

    public Customer update(CustomerDto customerDto, Long id) throws ParseException {
        Customer customer = convertToEntity(customerDto);
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer convertToEntity(CustomerDto customerDto) throws ParseException {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setBornAt(new SimpleDateFormat("yyyy-MM-dd").parse(customerDto.getBornAt()));
        return customer;
    }

    /**
     * Utility method to convert Customer to CustomerDTO
     *
     * @param customer: Customer to be converted
     * @return
     */
    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bornAt = dateFormat.format(customer.getBornAt());
        customerDto.setBornAt(bornAt);
        return customerDto;
    }
}
