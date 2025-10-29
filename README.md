# Mini Bank API

This project is a simple **bank account management system (Mini Bank) API** built with **Spring Boot**.  
It uses **REST API**, **DTO + Mapper**, **Transactional operations**, and **Spring Data JPA**.

---

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL (database)
- MapStruct (for Entity ↔ DTO mapping)
- Maven
- Postman (for API testing)

---

## Features

**Account Operations:**
   - Create an account
   - Delete an account
   - Check account balance (Read)
   - Deposit money into an account
   - Withdraw money from an account
   - Transfer money between accounts

**REST API Endpoints:**

| Method | Endpoint | Parameters | Description |
|--------|----------|------------|-------------|
| GET | `/accounts/all` | - | Retrieves all accounts |
| GET | `/accounts/{id}` | PathVariable `id` | Retrieves account by ID |
| POST | `/accounts/add/money` | `accountNumber`, `money` | Adds money to an account |
| POST | `/accounts/withdraw/money` | `accountNumber`, `money` | Withdraws money from an account |
| POST | `/accounts/transfer/money` | `fromAccountNumber`, `toAccountNumber`, `money` | Transfers money between accounts |
| GET | `/accounts/balance` | `accountNumber` | Retrieves account balance |
| DELETE | `/accounts` | `accountNumber` | Deletes an account |

---
