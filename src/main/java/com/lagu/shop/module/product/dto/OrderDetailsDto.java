package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.product.entity.OrderEntity;
import com.lagu.shop.module.product.entity.ProductEntity;

public class OrderDetailsDto {

    private OrderEntity order;

    private ProductEntity product;

    private Double price;

    private Integer amount;

    private Double total;

    public OrderEntity getOrder() {
        return order;
    }

    public OrderDetailsDto setOrder(OrderEntity order) {
        this.order = order;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public OrderDetailsDto setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderDetailsDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public OrderDetailsDto setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderDetailsDto setTotal(Double total) {
        this.total = total;
        return this;
    }

}
