package com.milleniumbank.accountapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private Long transactionId;

    private BigDecimal amount;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private String description;
}
