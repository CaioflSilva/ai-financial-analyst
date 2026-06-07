package com.aifinancialanalyst.presentation.response;

import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String description,
        BigDecimal amount,
        CategoryType type,
        LocalDate date,
        UUID categoryId
) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getCategoryId()
        );
    }
}