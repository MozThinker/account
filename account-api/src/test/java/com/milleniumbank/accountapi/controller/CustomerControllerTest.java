package com.milleniumbank.accountapi.controller;


import com.milleniumbank.accountapi.dto.CustomerDto;
import com.milleniumbank.accountapi.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Test
    void customerDetails_shouldReturnOkResponse() {
        // Arrange
        Long customerId = 1L;
        CustomerDto mockCustomerDto = new CustomerDto(/* your mocked CustomerDto */);

        when(customerService.getCustomerDetailsById(customerId)).thenReturn(mockCustomerDto);

        // Act
        ResponseEntity<CustomerDto> responseEntity = customerController.customerDetails(customerId);

        // Assert
        assertEquals(mockCustomerDto, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}

