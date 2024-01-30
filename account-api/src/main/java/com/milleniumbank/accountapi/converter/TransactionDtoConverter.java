package com.milleniumbank.accountapi.converter;

import com.milleniumbank.accountapi.dto.TransactionDto;
import com.milleniumbank.accountapi.model.Account;
import com.milleniumbank.accountapi.model.Transaction;
import com.milleniumbank.accountapi.model.TransactionType;
import org.springframework.stereotype.Component;


@Component
public class TransactionDtoConverter {
    public TransactionDto transactionToTransactionDto(Transaction transaction) {

        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getAmount(),
                transaction.getCreatedAt(),
                transaction.getTransactionType(),
                transaction.getDescription()
        );
    }
}