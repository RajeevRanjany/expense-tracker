package com.rajeev.expense_tracker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(nullable = false)
    private String category;

    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(unique = true)
    private String idempotencyKey;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
