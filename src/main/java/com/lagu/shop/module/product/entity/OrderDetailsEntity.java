package com.lagu.shop.module.product.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_generator")
    @SequenceGenerator(name = "order_details_generator", sequenceName = "order_details_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private Double total;

    public Long getId() {
        return id;
    }

    public OrderDetailsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public OrderDetailsEntity setOrder(OrderEntity order) {
        this.order = order;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public OrderDetailsEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderDetailsEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public OrderDetailsEntity setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderDetailsEntity setTotal(Double total) {
        this.total = total;
        return this;
    }

}
