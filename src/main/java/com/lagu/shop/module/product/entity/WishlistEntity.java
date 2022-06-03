package com.lagu.shop.module.product.entity;

import com.lagu.shop.module.user.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_generator")
    @SequenceGenerator(name = "wishlist_generator", sequenceName = "wishlist_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    public Long getId() {
        return id;
    }

    public WishlistEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public UserEntity getUser() {
        return user;
    }

    public WishlistEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public WishlistEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

}
