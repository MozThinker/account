package com.milleniumbank.accountapi.service;

import com.milleniumbank.accountapi.converter.AccountDtoConverter;
import com.milleniumbank.accountapi.dto.AccountDto;
import com.milleniumbank.accountapi.dto.CreateAccountRequestDto;
import com.milleniumbank.accountapi.model.*;
import com.milleniumbank.accountapi.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter accountDtoConverter, Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
        this.clock = clock;
    }

    public AccountDto createCurrentAccount(CreateAccountRequestDto createAccountRequestDto) {
        Customer customer = customerService.findCustomerById(createAccountRequestDto.getCustomerId());

        Account account = new Account(customer, createAccountRequestDto.getInitialCredit(), AccountType.CURRENT,getLocalDateTimeNow()
        );

        if (createAccountRequestDto.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(createAccountRequestDto.getInitialCredit(),
                    getLocalDateTimeNow(), TransactionType.INITIAL, "Initial credit", account);
            account.getTransactions().add(transaction);
        }

        return accountDtoConverter.accountToAccountDto(accountRepository.save(account));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}