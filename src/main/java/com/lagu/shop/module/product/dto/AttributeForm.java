package com.lagu.shop.module.product.dto;

public class AttributeForm {

    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public AttributeForm setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttributeForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AttributeForm setDescription(String description) {
        this.description = description;
        return this;
    }

}
