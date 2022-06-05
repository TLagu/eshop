package com.lagu.shop.module.product.dto;

public class CategoryForm {

    private Long id = 0L;

    private String name;

    private String description;

    private Long parent;

    public Long getId() {
        return id;
    }

    public CategoryForm setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getParent() {
        return parent;
    }

    public CategoryForm setParent(Long parent) {
        this.parent = parent;
        return this;
    }

    public boolean isNew() {
        return id == null || id == 0;
    }

}
