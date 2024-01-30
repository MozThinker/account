package com.milleniumbank.accountapi.converter;

import com.milleniumbank.accountapi.dto.CustomerAccountDto;
import com.milleniumbank.accountapi.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConverter converter;

    public CustomerAccountDtoConverter(TransactionDtoConverter converter) {
        this.converter = converter;
    }

    public CustomerAccountDto accountToCustomerAccountDto(Account account) {

        return new CustomerAccountDto(
                Objects.requireNonNull(account.getAccountId()),
                account.getBalance(),
                account.getCreatedAt(),
                account.getTransactions()
                        .stream()
                        .map(converter::transactionToTransactionDto)
                        .collect(Collectors.toSet())

        );
    }
}