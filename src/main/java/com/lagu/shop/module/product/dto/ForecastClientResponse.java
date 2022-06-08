package com.lagu.shop.module.product.dto;

import lombok.Data;

@Data
public class ForecastClientResponse {

    private ForecastCurrent current;

    @Data
    public static class ForecastCurrent {
        private Double temp;
        private Integer pressure;
        private Integer humidity;
    }

}
