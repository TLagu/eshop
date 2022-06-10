package com.lagu.shop.module.product.dto;

import javax.validation.constraints.Size;
import java.util.List;

public class CategoryForm {

    private Long id = 0L;
    @Size(min = 3, max = 25, message = "Długość powinny być pomiędzy 3 i 25")
    private String name;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String description;
    private Long parent;
    private List<TemplateForm> templates;

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

    public List<TemplateForm> getTemplates() {
        return templates;
    }

    public CategoryForm setTemplates(List<TemplateForm> templates) {
        this.templates = templates;
        return this;
    }

    public boolean isNew() {
        return id == null || id == 0;
    }

}
