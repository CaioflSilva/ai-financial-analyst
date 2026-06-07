package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.presentation.response.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDashboardUseCase {

    private final TransactionRepository transactionRepository;

    public DashboardResponse execute(UUID userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getType() == CategoryType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getType() == CategoryType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new DashboardResponse(
                totalIncome,
                totalExpense,
                balance,
                transactions.size()
        );
    }
}