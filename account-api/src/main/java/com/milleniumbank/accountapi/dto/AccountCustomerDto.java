package com.milleniumbank.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCustomerDto {
    private Long customerId;
    private String name;
    private String surname;
}
