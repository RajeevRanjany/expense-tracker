package com.rajeev.expense_tracker.service;

import com.rajeev.expense_tracker.model.Expense;
import com.rajeev.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense createExpense(Expense expense, String idempotencyKey) {

        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            Optional<Expense> existing = repository.findByIdempotencyKey(idempotencyKey);
            if (existing.isPresent()) {
                return existing.get(); // retry safe
            }
            expense.setIdempotencyKey(idempotencyKey);
        }

        return repository.save(expense);
    }

    public List<Expense> getExpenses(String category) {

        if (category != null && !category.isBlank()) {
            return repository.findByCategoryOrderByDateDesc(category);
        }

        return repository.findAllByOrderByDateDesc();
    }

    public BigDecimal calculateTotal(List<Expense> expenses) {

        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
