package com.milleniumbank.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountDto {

    private Long id;

    private BigDecimal balance = BigDecimal.ZERO;

    private LocalDateTime creationDate;

    private Set<TransactionDto> transactions;
}
