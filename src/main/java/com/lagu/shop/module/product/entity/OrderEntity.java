package com.lagu.shop.module.product.entity;

import com.lagu.shop.module.user.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_main")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_main_generator")
    @SequenceGenerator(name = "order_main_generator", sequenceName = "order_main_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "uuid")
    private String uuid;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Column(name = "street")
    private String street;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "post")
    private String post;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailsEntity> orderDetails;

    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getUuid() {
        return uuid;
    }

    public OrderEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public OrderEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public OrderEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPost() {
        return post;
    }

    public OrderEntity setPost(String post) {
        this.post = post;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderEntity setTotal(Double total) {
        this.total = total;
        return this;
    }

    public List<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    public OrderEntity setOrderDetails(List<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }
}
