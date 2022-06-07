package com.lagu.shop.module.product.dto;

public class OrderDetailsDto {

    private OrderDto order;

    private ProductDto product;

    private Double price;

    private Integer amount;

    private Double total;

    public OrderDto getOrder() {
        return order;
    }

    public OrderDetailsDto setOrder(OrderDto order) {
        this.order = order;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public OrderDetailsDto setProduct(ProductDto product) {
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
