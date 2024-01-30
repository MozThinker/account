package com.milleniumbank.accountapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDto {
        @Null(message = "Customer id must be null")
        @Schema(name = "customerId", example = "4")
        private Long customerId;

        @Min(value = 0, message = "Initial credit must be greater than or equal to zero")
        @Schema(name = "initialCredit", example = "100")
        private BigDecimal initialCredit;
}
