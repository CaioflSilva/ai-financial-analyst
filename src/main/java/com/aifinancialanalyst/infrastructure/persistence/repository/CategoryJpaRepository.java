package com.aifinancialanalyst.infrastructure.persistence.repository;

import com.aifinancialanalyst.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

    List<CategoryEntity> findAllByUserId(UUID userId);
}