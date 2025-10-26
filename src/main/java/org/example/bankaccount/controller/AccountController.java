package org.example.bankaccount.controller;

import org.example.bankaccount.dao.AccountDTO;

import org.example.bankaccount.module.Account;
import org.example.bankaccount.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }
    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add/money")
    public ResponseEntity<AccountDTO> addMoney(@RequestParam String accountNumber, @RequestParam Double money) {
        return ResponseEntity.ok(accountService.addMoney(accountNumber, money));
    }

    @PostMapping("/withdraw/money")
    public ResponseEntity<AccountDTO> withdrawMoney(@RequestParam String accountNumber, @RequestParam Double money) {
        return ResponseEntity.ok(accountService.withdrawMoney(accountNumber, money));

    }

    @PostMapping("/transfer/money")
    public ResponseEntity<Void> transferMoney(@RequestParam String fromAccountNumber,@RequestParam String toAccountNumber, @RequestParam Double money) {
        accountService.transferMoney(fromAccountNumber, toAccountNumber, money);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accountNumber) {
        Double balance = accountService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@RequestParam String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/control/{id}")
    public ResponseEntity<Account> getControlAccount(@PathVariable Long id) {
        Account account=accountService.getControlAccount(id);
        return ResponseEntity.ok(account);

    }
}