package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.CategoryNotFoundException;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void execute(UUID id, UUID userId) {
        categoryRepository.findById(id)
                .filter(category -> category.getUserId().equals(userId))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + id));

        categoryRepository.deleteById(id);
    }
}