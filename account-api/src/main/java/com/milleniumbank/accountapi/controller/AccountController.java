package com.milleniumbank.accountapi.controller;

import com.milleniumbank.accountapi.dto.AccountDto;
import com.milleniumbank.accountapi.dto.CreateAccountRequestDto;
import com.milleniumbank.accountapi.exception.CustomerNotFoundException;
import com.milleniumbank.accountapi.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {
    static final String BASE_URL = "/api/account";

    @Autowired
    private AccountService accountService;

    @Operation(summary = "This endpoint is to open a new Current Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New Current Account created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Could not create new Current Account, please check the request body",
                    content = @Content)
    })
    @PostMapping
    @Transactional
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CreateAccountRequestDto createAccountRequestDto) {
        return ResponseEntity.ok(accountService.createCurrentAccount(createAccountRequestDto));
    }
}
