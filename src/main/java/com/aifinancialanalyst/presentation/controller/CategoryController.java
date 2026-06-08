package com.aifinancialanalyst.presentation.controller;

import com.aifinancialanalyst.application.usecase.CreateCategoryUseCase;
import com.aifinancialanalyst.application.usecase.DeleteCategoryUseCase;
import com.aifinancialanalyst.application.usecase.GetCategoriesUseCase;
import com.aifinancialanalyst.application.usecase.UpdateCategoryUseCase;
import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.infrastructure.security.AuthenticatedUserService;
import com.aifinancialanalyst.presentation.request.CategoryRequest;
import com.aifinancialanalyst.presentation.response.CategoryResponse;
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
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoriesUseCase getCategoriesUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final AuthenticatedUserService authenticatedUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        Category category = createCategoryUseCase.execute(
                request.name(),
                request.type(),
                userId
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(CategoryResponse.from(category)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll(
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        List<CategoryResponse> categories = getCategoriesUseCase.execute(userId)
                .stream()
                .map(CategoryResponse::from)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody CategoryRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        Category category = updateCategoryUseCase.execute(
                id,
                request.name(),
                request.type(),
                userId
        );
        return ResponseEntity.ok(ApiResponse.success(CategoryResponse.from(category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = authenticatedUserService.getAuthenticatedUserId(userDetails);
        deleteCategoryUseCase.execute(id, userId);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully.", null));
    }
}