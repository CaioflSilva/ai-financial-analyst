package com.aifinancialanalyst.domain.repository;

import com.aifinancialanalyst.domain.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    Optional<Transaction> findById(UUID id);
    List<Transaction> findAllByUserId(UUID userId);
    List<Transaction> findAllByUserId(UUID userId, int page, int size);
    long countByUserId(UUID userId);
    void deleteById(UUID id);
}