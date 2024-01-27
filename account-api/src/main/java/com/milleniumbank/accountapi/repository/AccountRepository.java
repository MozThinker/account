package com.milleniumbank.accountapi.repository;

import com.milleniumbank.accountapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
