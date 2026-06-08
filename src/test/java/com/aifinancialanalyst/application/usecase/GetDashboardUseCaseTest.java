package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.presentation.response.DashboardResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetDashboardUseCase Tests")
class GetDashboardUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private GetDashboardUseCase getDashboardUseCase;

    @Test
    @DisplayName("Should return correct dashboard with income and expense")
    void shouldReturnCorrectDashboard() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        Transaction income = new Transaction(
                UUID.randomUUID(),
                "Salário",
                new BigDecimal("3500.00"),
                CategoryType.INCOME,
                LocalDate.now(),
                categoryId,
                userId
        );

        Transaction expense = new Transaction(
                UUID.randomUUID(),
                "Almoço",
                new BigDecimal("45.90"),
                CategoryType.EXPENSE,
                LocalDate.now(),
                categoryId,
                userId
        );

        when(transactionRepository.findAllByUserId(userId)).thenReturn(List.of(income, expense));

        // Act
        DashboardResponse result = getDashboardUseCase.execute(userId);

        // Assert
        assertNotNull(result);
        assertEquals(new BigDecimal("3500.00"), result.totalIncome());
        assertEquals(new BigDecimal("45.90"), result.totalExpense());
        assertEquals(new BigDecimal("3454.10"), result.balance());
        assertEquals(2, result.transactionCount());

        verify(transactionRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    @DisplayName("Should return zero values when no transactions exist")
    void shouldReturnZeroValuesWhenNoTransactions() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(transactionRepository.findAllByUserId(userId)).thenReturn(List.of());

        // Act
        DashboardResponse result = getDashboardUseCase.execute(userId);

        // Assert
        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result.totalIncome());
        assertEquals(BigDecimal.ZERO, result.totalExpense());
        assertEquals(BigDecimal.ZERO, result.balance());
        assertEquals(0, result.transactionCount());
    }
}