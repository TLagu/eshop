package com.lagu.shop.module.product.dto;

import java.util.Set;

public class ProductDto {

    private String uuid;
    private String model;
    private String description;
    private CategoryDto category;
    private Set<AttributeDto> attributes;

    public String getUuid() {
        return uuid;
    }

    public ProductDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ProductDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public ProductDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public Set<AttributeDto> getAttributes() {
        return attributes;
    }

    public ProductDto setAttributes(Set<AttributeDto> attributes) {
        this.attributes = attributes;
        return this;
    }
}
