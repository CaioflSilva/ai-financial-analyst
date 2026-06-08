package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.CategoryNotFoundException;
import com.aifinancialanalyst.domain.exception.TransactionNotFoundException;
import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Transaction execute(UUID id, String description, BigDecimal amount,
                               LocalDate date, UUID categoryId, UUID userId) {

        Transaction transaction = transactionRepository.findById(id)
                .filter(t -> t.getUserId().equals(userId))
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + id));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + categoryId));

        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setType(category.getType());
        transaction.setDate(date);
        transaction.setCategoryId(categoryId);

        return transactionRepository.save(transaction);
    }
}