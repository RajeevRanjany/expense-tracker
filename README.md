# Expense Tracker

Minimal full-stack Expense Tracker built with Spring Boot.

## Features

- Create expense (amount, category, description, date)
- View expenses
- Filter by category
- Sort by date (newest first)
- Total calculation
- Idempotent POST using Idempotency-Key header

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 (file-based DB)
- Thymeleaf

## Design Decisions

- Used BigDecimal for correct money handling.
- Used Idempotency-Key header to handle retries safely.
- Backend calculates totals to ensure correctness.
- Simple layered architecture (Controller → Service → Repository).

## Trade-offs

- H2 file database used for simplicity and time constraints.
- On cloud platforms with ephemeral storage, data may not persist across restarts.
- No pagination or authentication implemented.

## Run Locally

./mvnw spring-boot:run

Visit:
http://localhost:8080/ui


