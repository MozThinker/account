package com.milleniumbank.accountapi.service;

import com.milleniumbank.accountapi.TestSupport;
import com.milleniumbank.accountapi.dto.AccountCustomerDto;
import com.milleniumbank.accountapi.converter.AccountDtoConverter;
import com.milleniumbank.accountapi.dto.AccountDto;
import com.milleniumbank.accountapi.dto.CreateAccountRequestDto;
import com.milleniumbank.accountapi.dto.TransactionDto;
import com.milleniumbank.accountapi.exception.CustomerNotFoundException;
import com.milleniumbank.accountapi.model.*;
import com.milleniumbank.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest extends TestSupport {
    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter converter;
    private AccountService service;
    private final Customer customer = generateCustomer();

    private final AccountCustomerDto customerDto = new AccountCustomerDto(CUSTOMER_ID,
            CUSTOMER_NAME,
            CUSTOMER_SURNAME);

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerService = mock(CustomerService.class);
        converter = mock(AccountDtoConverter.class);
        Clock clock = mock(Clock.class);

        service = new AccountService(accountRepository, customerService, converter, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditMoreThanZero_shouldCreateAccountWithTransaction() {

        CreateAccountRequestDto request = generateCreateAccountRequest(100);

        Account account = generateAccount(100);
        Transaction transaction = new Transaction(request.getInitialCredit(), getLocalDateTime(), TransactionType.INITIAL, "Initial credit", account);
        account.getTransactions().add(transaction);

        TransactionDto transactionDto = new TransactionDto("",new BigDecimal(100), getLocalDateTime(), TransactionType.INITIAL, "Initial credit");
        AccountDto expected = new AccountDto(ACCOUNT_ID, new BigDecimal(100), AccountType.CURRENT, getLocalDateTime(), customerDto, List.of(transactionDto));

        when(customerService.findCustomerById(CUSTOMER_ID)).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);

        when(converter.accountToAccountDto(account)).thenReturn(expected);

        AccountDto result = service.createCurrentAccount(request);

        assertEquals(result, expected);

    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditIsZero_shouldCreateAccountWithoutTransaction() {
        CreateAccountRequestDto request = generateCreateAccountRequest(0);

        Account account = generateAccount(0);
        AccountDto expected = new AccountDto(ACCOUNT_ID, BigDecimal.ZERO, AccountType.CURRENT, getLocalDateTime(), customerDto, List.of());

        when(customerService.findCustomerById(CUSTOMER_ID)).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);
        when(converter.accountToAccountDto(account)).thenReturn(expected);

        AccountDto result = service.createCurrentAccount(request);

        assertEquals(result, expected);
    }


    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        CreateAccountRequestDto request = generateCreateAccountRequest(0);

        when(customerService.findCustomerById(CUSTOMER_ID)).thenThrow(new CustomerNotFoundException("test-exception"));

        assertThrows(CustomerNotFoundException.class,
                () -> service.createCurrentAccount(request));

        verify(customerService).findCustomerById(request.getCustomerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(converter);
    }
    private Account generateAccount(int balance) {
        return new Account(customer,new BigDecimal(balance),AccountType.CURRENT,getLocalDateTime());
    }
}