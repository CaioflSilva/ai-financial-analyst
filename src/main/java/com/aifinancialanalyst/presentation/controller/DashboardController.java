package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.GetDashboardUseCase;
import com.aifinancialanalyst.infrastructure.security.AuthenticatedUserService;
import com.aifinancialanalyst.presentation.response.DashboardResponse;
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
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final GetDashboardUseCase getDashboardUseCase;
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        DashboardResponse dashboard = getDashboardUseCase.execute(userId);
        return ResponseEntity.ok(ApiResponse.success(dashboard));
    }
}