package com.aifinancialanalyst.domain.model;

import java.util.UUID;

public class Category {

    private UUID id;
    private String name;
    private CategoryType type;
    private UUID userId;

    public Category() {}

    public Category(UUID id, String name, CategoryType type, UUID userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CategoryType getType() { return type; }
    public void setType(CategoryType type) { this.type = type; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}