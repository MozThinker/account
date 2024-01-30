package com.milleniumbank.accountapi.service;

import com.milleniumbank.accountapi.TestSupport;
import com.milleniumbank.accountapi.dto.CustomerDto;
import com.milleniumbank.accountapi.converter.CustomerDtoConverter;
import com.milleniumbank.accountapi.exception.CustomerNotFoundException;
import com.milleniumbank.accountapi.model.Customer;
import com.milleniumbank.accountapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest extends TestSupport {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        converter = Mockito.mock(CustomerDtoConverter.class);

        customerService = new CustomerService(customerRepository, converter);
    }

    @Test
    public void testFindCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer(CUSTOMER_ID,CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_EMAIL,CUSTOMER_PHONE, Set.of());
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer result = customerService.findCustomerById(1L);
        assertEquals(result, customer);
    }

    @Test
    public void testFindCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,
                () -> customerService.findCustomerById(CUSTOMER_ID));
    }


    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME,CUSTOMER_EMAIL, CUSTOMER_PHONE, Set.of());
        CustomerDto customerDto = new CustomerDto(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME, Set.of());

        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        Mockito.when(converter.customerToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerDetailsById(CUSTOMER_ID);
        assertEquals(result, customerDto);
    }


    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerDetailsById(CUSTOMER_ID));
        Mockito.verifyNoInteractions(converter);
    }

}
