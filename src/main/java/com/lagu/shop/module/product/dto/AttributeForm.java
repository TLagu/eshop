package com.lagu.shop.module.product.dto;

import javax.validation.constraints.Size;

public class AttributeForm {

    private Long id;
    private Long product;
    @Size(min = 3, max = 25, message = "Długość powinny być pomiędzy 3 i 25")
    private String name;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String description;

    public Long getId() {
        return id;
    }

    public AttributeForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProduct() {
        return product;
    }

    public AttributeForm setProduct(Long product) {
        this.product = product;
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
