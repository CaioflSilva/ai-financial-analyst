package com.aifinancialanalyst.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private CategoryType type;
    private LocalDate date;
    private UUID categoryId;
    private UUID userId;

    public Transaction() {}

    public Transaction(UUID id, String description, BigDecimal amount, CategoryType type,
                       LocalDate date, UUID categoryId, UUID userId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public CategoryType getType() { return type; }
    public void setType(CategoryType type) { this.type = type; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public UUID getCategoryId() { return categoryId; }
    public void setCategoryId(UUID categoryId) { this.categoryId = categoryId; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}