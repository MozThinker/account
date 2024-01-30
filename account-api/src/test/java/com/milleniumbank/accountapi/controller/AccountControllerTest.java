package com.milleniumbank.accountapi.controller;

import com.milleniumbank.accountapi.dto.AccountDto;
import com.milleniumbank.accountapi.dto.CreateAccountRequestDto;
import com.milleniumbank.accountapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    void createAccount_shouldReturnOkResponse() {
        // Arrange
        CreateAccountRequestDto requestDto = new CreateAccountRequestDto();
        AccountDto mockAccountDto = new AccountDto();

        when(accountService.createCurrentAccount(requestDto)).thenReturn(mockAccountDto);

        // Act
        ResponseEntity<AccountDto> responseEntity = accountController.createAccount(requestDto);

        // Assert
        assertEquals(mockAccountDto, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
