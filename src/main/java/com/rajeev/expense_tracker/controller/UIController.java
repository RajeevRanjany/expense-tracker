package com.rajeev.expense_tracker.controller;

import com.rajeev.expense_tracker.model.Expense;
import com.rajeev.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ui")
public class UIController {

    private final ExpenseService service;

    public UIController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public String viewExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            Model model
    ) {

        List<Expense> expenses = service.getExpenses(category);

        if ("date_desc".equals(sort)) {
            expenses = expenses.stream()
                    .sorted((e1, e2) -> e2.getDate().compareTo(e1.getDate()))
                    .toList();
        }

        BigDecimal total = service.calculateTotal(expenses);

        model.addAttribute("expenses", expenses);
        model.addAttribute("total", total);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedSort", sort);

        return "index";
    }


    @PostMapping("/add")
    public String addExpense(
            @Valid Expense expense,
            org.springframework.validation.BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {

            List<Expense> expenses = service.getExpenses(null);
            model.addAttribute("expenses", expenses);
            model.addAttribute("total", service.calculateTotal(expenses));

            return "index";
        }

        service.createExpense(expense, UUID.randomUUID().toString());
        return "redirect:/ui";
    }

}
