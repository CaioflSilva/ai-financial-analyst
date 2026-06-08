package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.CategoryNotFoundException;
import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateTransactionUseCase Tests")
class CreateTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CreateTransactionUseCase createTransactionUseCase;

    @Test
    @DisplayName("Should create transaction successfully")
    void shouldCreateTransactionSuccessfully() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        Category category = new Category(categoryId, "Alimentação", CategoryType.EXPENSE, userId);

        Transaction savedTransaction = new Transaction(
                UUID.randomUUID(),
                "Almoço restaurante",
                new BigDecimal("45.90"),
                CategoryType.EXPENSE,
                LocalDate.now(),
                categoryId,
                userId
        );

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        // Act
        Transaction result = createTransactionUseCase.execute(
                "Almoço restaurante",
                new BigDecimal("45.90"),
                LocalDate.now(),
                categoryId,
                userId
        );

        // Assert
        assertNotNull(result);
        assertEquals("Almoço restaurante", result.getDescription());
        assertEquals(new BigDecimal("45.90"), result.getAmount());
        assertEquals(CategoryType.EXPENSE, result.getType());

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Should throw CategoryNotFoundException when category does not exist")
    void shouldThrowExceptionWhenCategoryNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                CategoryNotFoundException.class,
                () -> createTransactionUseCase.execute(
                        "Almoço restaurante",
                        new BigDecimal("45.90"),
                        LocalDate.now(),
                        categoryId,
                        userId
                )
        );

        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}