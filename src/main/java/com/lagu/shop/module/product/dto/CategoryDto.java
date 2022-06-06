package com.lagu.shop.module.product.dto;

public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private CategoryDto parent;

    public Long getId() {
        return id;
    }

    public CategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryDto getParent() {
        return parent;
    }

    public CategoryDto setParent(CategoryDto parent) {
        this.parent = parent;
        return this;
    }

    public boolean isNew() {
        return id == null || id == 0;
    }

}
