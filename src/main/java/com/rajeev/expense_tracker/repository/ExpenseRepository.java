package com.rajeev.expense_tracker.repository;

import com.rajeev.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdempotencyKey(String idempotencyKey);

    List<Expense> findByCategoryOrderByDateDesc(String category);

    List<Expense> findAllByOrderByDateDesc();
}
