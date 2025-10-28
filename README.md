Book Rental API

This project is a simple book rental management system API built with Spring Boot.
It uses REST API, DTO + Mapper, Transactional operations, and Spring Data JPA.

Technologies

Java 17

Spring Boot 3.x

Spring Data JPA

MySQL (or any relational database)

MapStruct (for Entity ↔ DTO mapping)

Maven

Postman (for API testing)

Features
Book Operations:

Add a book

Update book information

Delete a book

List all available books

Rental Operations:

Rent a book

Return a book

List all rentals of a user

REST API Endpoints
Book Endpoints:
Method	Endpoint	Parameters	Description
GET	/book	-	Retrieves all available books
POST	/book	Book JSON	Adds a new book
PUT	/book/{id}	Book JSON	Updates an existing book
DELETE	/book/{id}	-	Deletes a book by ID
Rental Endpoints:
Method	Endpoint	Parameters	Description
POST	/rental	userId, bookId	Rent a book
PUT	/rental/{id}/return	-	Return a rented book
GET	/rental/user/{userId}	-	Get all rentals of a specific user
