package com.aifinancialanalyst.infrastructure.persistence.adapter;

import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.repository.CategoryRepository;
import com.aifinancialanalyst.infrastructure.persistence.entity.CategoryEntity;
import com.aifinancialanalyst.infrastructure.persistence.entity.UserEntity;
import com.aifinancialanalyst.infrastructure.persistence.repository.CategoryJpaRepository;
import com.aifinancialanalyst.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository jpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Category save(Category category) {
        UserEntity user = userJpaRepository.findById(category.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        CategoryEntity entity = toEntity(category, user);
        CategoryEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Category> findAllByUserId(UUID userId) {
        return jpaRepository.findAllByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private CategoryEntity toEntity(Category category, UserEntity user) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .user(user)
                .build();
    }

    private Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getUser().getId()
        );
    }
}