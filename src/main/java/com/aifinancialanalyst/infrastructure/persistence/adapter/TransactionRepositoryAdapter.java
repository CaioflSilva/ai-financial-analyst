package com.aifinancialanalyst.infrastructure.persistence.adapter;

import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.domain.repository.TransactionRepository;
import com.aifinancialanalyst.infrastructure.persistence.entity.CategoryEntity;
import com.aifinancialanalyst.infrastructure.persistence.entity.TransactionEntity;
import com.aifinancialanalyst.infrastructure.persistence.entity.UserEntity;
import com.aifinancialanalyst.infrastructure.persistence.repository.CategoryJpaRepository;
import com.aifinancialanalyst.infrastructure.persistence.repository.TransactionJpaRepository;
import com.aifinancialanalyst.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Transaction save(Transaction transaction) {
        UserEntity user = userJpaRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        CategoryEntity category = categoryJpaRepository.findById(transaction.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        TransactionEntity entity = toEntity(transaction, user, category);
        TransactionEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId) {
        return jpaRepository.findAllByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return jpaRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public long countByUserId(UUID userId) {
        return jpaRepository.countByUserId(userId);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private TransactionEntity toEntity(Transaction transaction, UserEntity user, CategoryEntity category) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .category(category)
                .user(user)
                .build();
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getDescription(),
                entity.getAmount(),
                entity.getType(),
                entity.getDate(),
                entity.getCategory().getId(),
                entity.getUser().getId()
        );
    }
}