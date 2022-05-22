package com.lagu.shop.core.error;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorMessageMapper {
    public static List<String> map(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(item -> item.getDefaultMessage())
                .collect(Collectors.toList());
    }
}