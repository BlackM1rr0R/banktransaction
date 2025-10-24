package org.example.bankaccount.repository;

import org.example.bankaccount.dao.AccountDTO;
import org.example.bankaccount.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
