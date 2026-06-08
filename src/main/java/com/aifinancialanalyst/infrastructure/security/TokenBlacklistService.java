package com.aifinancialanalyst.infrastructure.security;

import com.aifinancialanalyst.infrastructure.persistence.entity.TokenBlacklistEntity;
import com.aifinancialanalyst.infrastructure.persistence.repository.TokenBlacklistJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final TokenBlacklistJpaRepository tokenBlacklistJpaRepository;
    private final JwtService jwtService;

    public void blacklistToken(String token) {
        String email = jwtService.extractEmail(token);
        LocalDateTime expiresAt = jwtService.extractExpiration(token)
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

        TokenBlacklistEntity blacklistedToken = TokenBlacklistEntity.builder()
                .token(token)
                .expiresAt(expiresAt)
                .build();

        tokenBlacklistJpaRepository.save(blacklistedToken);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistJpaRepository.existsByToken(token);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cleanExpiredTokens() {
        tokenBlacklistJpaRepository.deleteExpiredTokens(LocalDateTime.now());
    }
}