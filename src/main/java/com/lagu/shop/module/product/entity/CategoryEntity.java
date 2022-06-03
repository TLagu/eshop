package com.lagu.shop.module.product.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryEntity> children;

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

    public List<CategoryEntity> getChildren() {
        return children;
    }

    public CategoryEntity setChildren(List<CategoryEntity> children) {
        this.children = children;
        return this;
    }
}
