package com.milleniumbank.accountapi.service;

import com.milleniumbank.accountapi.dto.CustomerDto;
import com.milleniumbank.accountapi.converter.CustomerDtoConverter;
import com.milleniumbank.accountapi.exception.CustomerNotFoundException;
import com.milleniumbank.accountapi.model.Customer;
import com.milleniumbank.accountapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(Long id) {
        //validations.forEach(validation -> validation.validate(id));
        //return customerRepository.getReferenceById(id);
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public CustomerDto getCustomerDetailsById(Long customerId) {
        return customerDtoConverter.customerToCustomerDto(findCustomerById(customerId));
    }

}
