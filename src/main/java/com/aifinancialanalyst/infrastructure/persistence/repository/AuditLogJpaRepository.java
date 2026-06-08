package com.aifinancialanalyst.infrastructure.persistence.repository;

import com.aifinancialanalyst.infrastructure.persistence.entity.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditLogJpaRepository extends JpaRepository<AuditLogEntity, UUID> {

    List<AuditLogEntity> findAllByUserIdOrderByCreatedAtDesc(UUID userId);
}