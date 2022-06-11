package com.lagu.shop.module.product.entity;

import com.lagu.shop.module.user.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
public class CartEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_generator")
    @SequenceGenerator(name = "card_generator", sequenceName = "card_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private Double total;

    public Long getId() {
        return id;
    }

    public CartEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public UserEntity getUser() {
        return user;
    }

    public CartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CartEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public CartEntity setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public CartEntity setTotal(Double total) {
        this.total = total;
        return this;
    }

    public void addAmount() {
        this.amount++;
        this.total = this.getProduct().getPrice() * this.amount;
    }

    public void removeAmount() {
        if (this.amount > 1) {
            this.amount--;
            this.total = this.getProduct().getPrice() * this.amount;
        }
    }

}
