package com.lagu.shop.module.product.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class ProductForm implements Serializable {

    private String uuid;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String model;
    @Size(min = 3, max = 10000, message = "Długość powinny być pomiędzy 3 i 10000")
    private String description;
    private Long category;
    private List<AttributeForm> attributes;
    @DecimalMin(value = "0.01", message = "Wartość minimalna to 0.01")
    @DecimalMax(value = "100000.0", message = "Wartość maksymalna to 1000000.0")
    private Double price;
    @Size(max = 200, message = "Długość powinna być mniejsza niż 200")
    private String path;
    @Size(max = 50, message = "Długość powinna być mniejsza niż 50")
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

    public List<AttributeForm> getAttributes() {
        return attributes;
    }

    public ProductForm setAttributes(List<AttributeForm> attributes) {
        this.attributes = attributes;
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
