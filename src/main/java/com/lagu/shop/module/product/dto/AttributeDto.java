package com.lagu.shop.module.product.dto;

public class AttributeDto {

    private Long id;
    private ProductDto product;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public AttributeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public AttributeDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttributeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AttributeDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
