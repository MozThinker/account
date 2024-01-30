package com.milleniumbank.accountapi.dto;

import com.milleniumbank.accountapi.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private TransactionType transactionType;
    private String description;
}
