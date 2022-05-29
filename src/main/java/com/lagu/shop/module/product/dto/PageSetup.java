package com.lagu.shop.module.product.dto;

public class PageSetup {
    private String uri;
    private boolean isLogged;

    public PageSetup(String uri, boolean isLogged) {
        this.uri = uri;
        this.isLogged = isLogged;
    }

    public String getUri() {
        return uri;
    }

    public boolean isLogged() {
        return isLogged;
    }
}
