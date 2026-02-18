# Expense Tracker

Minimal full-stack Expense Tracker built with Spring Boot.

----------------------------------------

FEATURES

- Create expense (amount, category, description, date)
- View expenses
- Filter by category
- Sort by date (newest first)
- Total calculation
- Idempotent POST using Idempotency-Key header

----------------------------------------

TECH STACK

- Java 17
- Spring Boot
- Spring Data JPA
- H2 (file-based database)
- Thymeleaf

----------------------------------------

CLONE AND RUN LOCALLY

1. Clone the repository

git clone https://github.com/RajeevRanjany/expense-tracker.git
cd expense-tracker

2. Run the application

./mvnw spring-boot:run

3. Open in browser

http://localhost:8080/ui

----------------------------------------

DESIGN DECISIONS

- Used BigDecimal for correct money handling.
- Implemented Idempotency-Key header to handle retry-safe POST requests.
- Backend calculates totals to ensure correctness.
- Clean layered architecture (Controller → Service → Repository).
- H2 file database chosen for simplicity.

----------------------------------------

TRADE-OFFS

- H2 file-based DB used due to time constraints.
- On cloud platforms with ephemeral storage, data may not persist across restarts.
- No pagination or authentication implemented.

----------------------------------------

This implementation focuses on correctness, data safety, and production-like structure within a limited timebox.

Author : 
Rajeev Ranjan
MNNIT ALLAHABAD