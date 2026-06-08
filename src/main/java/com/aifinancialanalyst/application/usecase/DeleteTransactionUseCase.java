package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.TransactionNotFoundException;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public void execute(UUID id, UUID userId) {
        transactionRepository.findById(id)
                .filter(transaction -> transaction.getUserId().equals(userId))
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + id));

        transactionRepository.deleteById(id);
    }
}