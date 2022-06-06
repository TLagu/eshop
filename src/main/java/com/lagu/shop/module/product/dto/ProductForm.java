package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.product.entity.CategoryEntity;

import java.util.Set;

public class ProductForm {

    private String uuid;
    private String model;
    private String description;
    private Long category;
//    private Set<AttributeDto> attributes;
    private Double price;
    private String path;
    private String code;

    public String getUuid() {
        return uuid;
    }

    public ProductForm setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ProductForm setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCategory() {
        return category;
    }

    public ProductForm setCategory(Long category) {
        this.category = category;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductForm setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ProductForm setPath(String path) {
        this.path = path;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProductForm setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
