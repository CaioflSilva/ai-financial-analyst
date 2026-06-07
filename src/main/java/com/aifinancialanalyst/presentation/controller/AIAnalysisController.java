package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.AnalyzeFinancesUseCase;
import com.aifinancialanalyst.infrastructure.security.AuthenticatedUserService;
import com.aifinancialanalyst.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AIAnalysisController {

    private final AnalyzeFinancesUseCase analyzeFinancesUseCase;
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping("/analyze")
    public ResponseEntity<ApiResponse<String>> analyze(
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        String analysis = analyzeFinancesUseCase.execute(userId);
        return ResponseEntity.ok(ApiResponse.success(analysis));
    }
}