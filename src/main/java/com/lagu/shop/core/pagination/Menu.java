package com.lagu.shop.core.pagination;

public class BottomMenu {
    private String className;
    private String textValue;
    private String url;

    public BottomMenu(String className, String textValue, String url) {
        this.className = className;
        this.textValue = textValue;
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getUrl() {
        return url;
    }

    public BottomMenu setClassName(String className) {
        this.className = className;
        return this;
    }
}
