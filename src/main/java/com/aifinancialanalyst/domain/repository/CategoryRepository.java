package com.aifinancialanalyst.domain.repository;

import com.aifinancialanalyst.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Category save(Category category);
    Optional<Category> findById(UUID id);
    List<Category> findAllByUserId(UUID userId);
    void deleteById(UUID id);
}