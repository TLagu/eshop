package com.lagu.shop.core.error;

import java.util.List;

public class ErrorResponse {

    private final List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}