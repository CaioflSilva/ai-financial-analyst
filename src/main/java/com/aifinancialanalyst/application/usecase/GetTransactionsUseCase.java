package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    public List<Transaction> execute(UUID userId) {
        return transactionRepository.findAllByUserId(userId);
    }
}