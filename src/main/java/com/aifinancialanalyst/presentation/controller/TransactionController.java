package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.CreateTransactionUseCase;
import com.aifinancialanalyst.application.usecase.GetTransactionsUseCase;
import com.aifinancialanalyst.domain.model.Transaction;
import com.aifinancialanalyst.infrastructure.security.AuthenticatedUserService;
import com.aifinancialanalyst.presentation.request.TransactionRequest;
import com.aifinancialanalyst.presentation.response.TransactionResponse;
import com.aifinancialanalyst.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;
    private final AuthenticatedUserService authenticatedUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> create(
            @Valid @RequestBody TransactionRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        Transaction transaction = createTransactionUseCase.execute(
                request.description(),
                request.amount(),
                request.date(),
                request.categoryId(),
                userId
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(TransactionResponse.from(transaction)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAll(
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        List<TransactionResponse> transactions = getTransactionsUseCase.execute(userId)
                .stream()
                .map(TransactionResponse::from)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(transactions));
    }
}