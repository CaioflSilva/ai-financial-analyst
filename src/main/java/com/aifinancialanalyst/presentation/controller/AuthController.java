package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.AuthenticateUserUseCase;
import com.aifinancialanalyst.application.usecase.CreateUserUseCase;
import com.aifinancialanalyst.domain.model.User;
import com.aifinancialanalyst.infrastructure.security.TokenBlacklistService;
import com.aifinancialanalyst.presentation.request.LoginRequest;
import com.aifinancialanalyst.presentation.request.RegisterRequest;
import com.aifinancialanalyst.presentation.response.AuthResponse;
import com.aifinancialanalyst.shared.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        User user = createUserUseCase.execute(
                request.name(),
                request.email(),
                request.password()
        );
        String token = authenticateUserUseCase.execute(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(new AuthResponse(token)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        String token = authenticateUserUseCase.execute(request.email(), request.password());
        return ResponseEntity.ok(ApiResponse.success(new AuthResponse(token)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
        return ResponseEntity.ok(ApiResponse.success("Logout realizado com sucesso.", null));
    }
}