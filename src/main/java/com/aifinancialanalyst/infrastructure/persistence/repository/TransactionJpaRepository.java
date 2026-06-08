package com.aifinancialanalyst.infrastructure.persistence.repository;

import com.aifinancialanalyst.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findAllByUserId(UUID userId);
    Page<TransactionEntity> findAllByUserId(UUID userId, Pageable pageable);
    long countByUserId(UUID userId);
}