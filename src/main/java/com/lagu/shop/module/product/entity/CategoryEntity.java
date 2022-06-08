package com.lagu.shop.module.product.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status", columnDefinition = "varchar(25) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<CategoryEntity> children;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TemplateEntity> templates;

    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public CategoryEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public CategoryEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public CategoryEntity setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public CategoryEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public CategoryEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public CategoryEntity setParent(CategoryEntity parent) {
        this.parent = parent;
        return this;
    }

    public Set<CategoryEntity> getChildren() {
        return children;
    }

    public CategoryEntity setChildren(Set<CategoryEntity> children) {
        this.children = children;
        return this;
    }

    public Set<TemplateEntity> getTemplates() {
        return templates;
    }

    public CategoryEntity setTemplates(Set<TemplateEntity> templates) {
        this.templates = templates;
        return this;
    }
}
