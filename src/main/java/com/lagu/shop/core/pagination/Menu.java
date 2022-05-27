package com.lagu.shop.core.pagination;

public class Menu {
    private String className;
    private String textValue;
    private String url;

    public Menu(String className, String textValue, String url) {
        this.className = className;
        this.textValue = textValue;
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public Menu setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getUrl() {
        return url;
    }

    public Menu setUrl(String url) {
        this.url = url;
        return this;
    }
}
