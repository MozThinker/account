package com.milleniumbank.accountapi.dto;

import com.milleniumbank.accountapi.model.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long accountId;
    private BigDecimal balance;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private AccountCustomerDto owner;
    private List<TransactionDto> transactions;
}
