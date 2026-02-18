package com.rajeev.expense_tracker.controller;

import com.rajeev.expense_tracker.model.Expense;
import com.rajeev.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @Valid @RequestBody Expense expense,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey
    ) {

        Expense saved = service.createExpense(expense, idempotencyKey);
        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort
    ) {

        List<Expense> expenses = service.getExpenses(category);

        if ("date_desc".equals(sort)) {
            expenses = expenses.stream()
                    .sorted((e1, e2) -> e2.getDate().compareTo(e1.getDate()))
                    .toList();
        }

        BigDecimal total = service.calculateTotal(expenses);

        Map<String, Object> response = new HashMap<>();
        response.put("expenses", expenses);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }





}
