package com.lagu.shop.module.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {
    private Double temperature;
    private Integer pressure;
    private Integer humidity;
}
