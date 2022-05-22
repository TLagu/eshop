package com.lagu.shop.core.pagination;

public class Metadata {
    private final int totalPages;
    private final long totalElements;
    private int page;
    private int size;

    public Metadata(int totalPages, long totalElements, int size, int page) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public Metadata setPage(int page) {
        this.page = page;
        return this;
    }

    public Metadata setSize(int size) {
        this.size = size;
        return this;
    }
}
