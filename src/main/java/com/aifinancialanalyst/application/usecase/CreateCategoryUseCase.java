package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.CategoryType;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Category execute(String name, CategoryType type, UUID userId) {
        Category category = new Category();
        category.setName(name);
        category.setType(type);
        category.setUserId(userId);
        return categoryRepository.save(category);
    }
}