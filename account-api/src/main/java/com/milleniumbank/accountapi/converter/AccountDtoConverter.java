package com.milleniumbank.accountapi.converter;

import com.milleniumbank.accountapi.dto.AccountDto;
import com.milleniumbank.accountapi.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter,
                               TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto accountToAccountDto(Account account) {

        return new AccountDto(
                account.getAccountId(),
                account.getBalance(),
                account.getAccountType(),
                account.getCreatedAt(),
                customerDtoConverter.customerToAccountCustomerDto(account.getOwner()),
                account.getTransactions()
                        .stream()
                        .map(transactionDtoConverter::transactionToTransactionDto)
                        .collect(Collectors.toList())
        );


    }
}