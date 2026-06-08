package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.CategoryNotFoundException;
import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @org.springframework.transaction.annotation.Transactional
    public Category execute(UUID id, String name, CategoryType type, UUID userId) {
        Category category = categoryRepository.findById(id)
                .filter(c -> c.getUserId().equals(userId))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + id));

        category.setName(name);
        category.setType(type);

        return categoryRepository.save(category);
    }
}