package org.example.bankaccount.service;

import jakarta.transaction.Transactional;
import org.example.bankaccount.dao.AccountDTO;
import org.example.bankaccount.mapper.AccountMapper;
import org.example.bankaccount.module.Account;
import org.example.bankaccount.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    public AccountService(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AccountDTO> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public AccountDTO addMoney(String accountNumber, Double money) {
        //Accountu DB de tapirig
        Account account = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        //Balansi artiririg
        account.setBalance(account.getBalance() + money);

        Account updatedAccount = accountRepository.save(account);
        return mapper.toDto(updatedAccount);
    }

    @Transactional
    public AccountDTO withdrawMoney(String accountNumber, Double money) {
        Account account = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < money) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insufficient funds");
        }

        account.setBalance(account.getBalance() - money);

        Account updatedAccount = accountRepository.save(account);
        return mapper.toDto(updatedAccount);
    }

    @Transactional
    public void transferMoney(String fromAccountNumber, String toAccountNumber, Double money) {
        if (money <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insufficient funds");

        Account fromAccount = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(fromAccountNumber))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"From account not found"));

        Account toAccount = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(toAccountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (fromAccount.getBalance() < money) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Insufficient funds");

        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        System.out.println(fromAccount.getBalance());
        System.out.println(toAccount.getBalance());
        System.out.println("Transfer successful");
    }

    public Double getBalance(String accountNumber) {
        Account account = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    public Account getControlAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        return mapper.toDto(accountRepository.save(mapper.toEntity(accountDTO)));
    }
}
