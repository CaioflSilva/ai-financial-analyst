package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.CategoryNotFoundException;
import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public Transaction execute(String description, BigDecimal amount,
                               LocalDate date, UUID categoryId, UUID userId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + categoryId));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setType(category.getType());
        transaction.setDate(date);
        transaction.setCategoryId(categoryId);
        transaction.setUserId(userId);

        return transactionRepository.save(transaction);
    }
}