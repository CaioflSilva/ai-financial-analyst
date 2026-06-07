package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.AuthenticateUserUseCase;
import com.aifinancialanalyst.application.usecase.CreateUserUseCase;
import com.aifinancialanalyst.domain.model.User;
import com.aifinancialanalyst.presentation.request.LoginRequest;
import com.aifinancialanalyst.presentation.request.RegisterRequest;
import com.aifinancialanalyst.presentation.response.AuthResponse;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = createUserUseCase.execute(
                request.name(),
                request.email(),
                request.password()
        );
        String token = authenticateUserUseCase.execute(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authenticateUserUseCase.execute(request.email(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}