package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.shared.response.PagedResponse;
import com.aifinancialanalyst.presentation.response.TransactionResponse;
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

    public PagedResponse<TransactionResponse> execute(UUID userId, int page, int size) {
        List<TransactionResponse> transactions = transactionRepository
                .findAllByUserId(userId, page, size)
                .stream()
                .map(TransactionResponse::from)
                .toList();

        long total = transactionRepository.countByUserId(userId);
        return PagedResponse.of(transactions, page, size, total);
    }
}