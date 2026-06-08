package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.BusinessException;
import com.aifinancialanalyst.domain.model.User;
import com.aifinancialanalyst.domain.model.UserRole;
import com.aifinancialanalyst.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateUserUseCase Tests")
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User(
                UUID.randomUUID(),
                "Caio Filipe",
                "caio@teste.com",
                "encodedPassword",
                UserRole.USER,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Should create user successfully when email is not in use")
    void shouldCreateUserSuccessfully() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User result = createUserUseCase.execute("Caio Filipe", "caio@teste.com", "123456");

        assertNotNull(result);
        assertEquals("Caio Filipe", result.getName());
        assertEquals("caio@teste.com", result.getEmail());
        assertEquals(UserRole.USER, result.getRole());

        verify(userRepository, times(1)).existsByEmail("caio@teste.com");
        verify(passwordEncoder, times(1)).encode("123456");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when email already exists")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> createUserUseCase.execute("Caio Filipe", "caio@teste.com", "123456")
        );

        assertEquals("Email already in use: caio@teste.com", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}