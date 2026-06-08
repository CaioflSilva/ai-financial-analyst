package com.aifinancialanalyst.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JwtService Tests")
class JwtServiceTest {

    private JwtService jwtService;

    private static final String SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final Long EXPIRATION = 86400000L;
    private static final String EMAIL = "caio@teste.com";

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", SECRET);
        ReflectionTestUtils.setField(jwtService, "expiration", EXPIRATION);
    }

    @Test
    @DisplayName("Should generate a valid token")
    void shouldGenerateValidToken() {
        String token = jwtService.generateToken(EMAIL);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    @DisplayName("Should extract email from token")
    void shouldExtractEmailFromToken() {
        String token = jwtService.generateToken(EMAIL);
        String extractedEmail = jwtService.extractEmail(token);

        assertEquals(EMAIL, extractedEmail);
    }

    @Test
    @DisplayName("Should validate token successfully")
    void shouldValidateTokenSuccessfully() {
        String token = jwtService.generateToken(EMAIL);
        boolean isValid = jwtService.isTokenValid(token, EMAIL);

        assertTrue(isValid);
    }

    @Test
    @DisplayName("Should invalidate token with wrong email")
    void shouldInvalidateTokenWithWrongEmail() {
        String token = jwtService.generateToken(EMAIL);
        boolean isValid = jwtService.isTokenValid(token, "outro@email.com");

        assertFalse(isValid);
    }
}