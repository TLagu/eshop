package com.lagu.shop.module.product.dto;

public class PageSetup {
    private final String uri;
    private final boolean isLogged;

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
