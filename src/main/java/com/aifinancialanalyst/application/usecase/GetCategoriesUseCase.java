package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public List<Category> execute(UUID userId) {
        return categoryRepository.findAllByUserId(userId);
    }
}