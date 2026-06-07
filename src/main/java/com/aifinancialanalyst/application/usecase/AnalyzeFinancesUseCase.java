package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.infrastructure.ia.FinancialAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyzeFinancesUseCase {

    private final TransactionRepository transactionRepository;
    private final FinancialAIService financialAIService;

    public List<String> execute(UUID userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);

        if (transactions.isEmpty()) {
            return List.of("Você ainda não possui transações cadastradas. Adicione suas receitas e despesas para receber uma análise personalizada.");
        }

        return financialAIService.analyzeTransactions(transactions);
    }
}