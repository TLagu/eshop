package com.lagu.shop.module.product.dto;

public class ProductForm {

    private String uuid;
    private String model;
    private String description;

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

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
