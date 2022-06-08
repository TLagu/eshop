package com.lagu.shop.module.product.dto;

public class TemplateForm {

    private Long id;
    private String status;
    private Long category;
    private String name;

    public Long getId() {
        return id;
    }

    public TemplateForm setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TemplateForm setStatus(String status) {
        this.status = status;
        return this;
    }

    public Long getCategory() {
        return category;
    }

    public TemplateForm setCategory(Long category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public TemplateForm setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isNew() {
        return id == null || id == 0;
    }

}
