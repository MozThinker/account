package com.milleniumbank.accountapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction(BigDecimal amount, LocalDateTime createdAt, TransactionType transactionType , String description, Account account) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.transactionType = transactionType;
        this.description = description;
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction transaction = (Transaction) o;

        return Objects.equals(transactionId, transaction.transactionId) &&
                Objects.equals(amount, transaction.amount) &&
                Objects.equals(createdAt, transaction.createdAt) &&
                transactionType == transaction.transactionType &&
                Objects.equals(description, transaction.description) &&
                Objects.equals(account, transaction.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, createdAt, transactionType, description, account);
    }

}
