package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.infrastructure.ai.FinancialAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatWithAIUseCase {

    private final TransactionRepository transactionRepository;
    private final FinancialAIService financialAIService;

    public String execute(String message, UUID userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);
        return financialAIService.chat(message, transactions);
    }
}