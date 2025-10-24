package org.example.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankaccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankaccountApplication.class, args);
        System.out.println("Bank Account Application is running...");
    }

}
