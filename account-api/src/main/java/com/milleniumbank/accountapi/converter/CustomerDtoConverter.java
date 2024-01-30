package com.milleniumbank.accountapi.converter;

import com.milleniumbank.accountapi.dto.AccountCustomerDto;
import com.milleniumbank.accountapi.dto.CustomerDto;
import com.milleniumbank.accountapi.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter customerAccountDtoConverter) {
        this.customerAccountDtoConverter = customerAccountDtoConverter;
    }

    public AccountCustomerDto customerToAccountCustomerDto(Customer customer) {

        if (customer == null) {
            return new AccountCustomerDto(null, "", "");
        }
        return new AccountCustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getSurname()
        );
    }

    public CustomerDto customerToCustomerDto(Customer customer) {

        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getSurname(),
                customer.getAccounts()
                        .stream()
                        .map(customerAccountDtoConverter::accountToCustomerAccountDto)
                        .collect(Collectors.toSet())
        );
    }
}
