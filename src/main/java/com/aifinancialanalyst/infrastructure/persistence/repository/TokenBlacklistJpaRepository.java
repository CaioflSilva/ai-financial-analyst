package com.aifinancialanalyst.infrastructure.persistence.repository;

import com.aifinancialanalyst.infrastructure.persistence.entity.TokenBlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TokenBlacklistJpaRepository extends JpaRepository<TokenBlacklistEntity, UUID> {

    boolean existsByToken(String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM TokenBlacklistEntity t WHERE t.expiresAt < :now")
    void deleteExpiredTokens(LocalDateTime now);
}