package com.aifinancialanalyst.infrastructure.security;

import com.aifinancialanalyst.infrastructure.persistence.entity.AuditLogEntity;
import com.aifinancialanalyst.infrastructure.persistence.repository.AuditLogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogJpaRepository auditLogJpaRepository;

    @Async
    public void log(UUID userId, String action, String resource, String resourceId,
                    String details, String ipAddress) {
        try {
            AuditLogEntity auditLog = AuditLogEntity.builder()
                    .userId(userId)
                    .action(action)
                    .resource(resource)
                    .resourceId(resourceId)
                    .details(details)
                    .ipAddress(ipAddress)
                    .build();

            auditLogJpaRepository.save(auditLog);
        } catch (Exception e) {
            log.error("Failed to save audit log: {}", e.getMessage());
        }
    }
}