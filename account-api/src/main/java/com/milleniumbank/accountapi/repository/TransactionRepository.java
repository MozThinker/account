package com.milleniumbank.accountapi.repository;

import com.milleniumbank.accountapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
