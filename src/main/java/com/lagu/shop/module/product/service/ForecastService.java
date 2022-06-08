package com.lagu.shop.module.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.shop.module.product.dto.Forecast;
import com.lagu.shop.module.product.dto.ForecastClientResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class ForecastService {

    private final static String API_KEY = "a7f56c4235d1e36534313e315ea5e35a";

    private Forecast mapToForecast(ForecastClientResponse.ForecastCurrent fcr) {
        Forecast forecast = new Forecast();
        forecast.setTemperature(Objects.requireNonNull(fcr).getTemp());
        forecast.setHumidity(Objects.requireNonNull(fcr).getHumidity());
        forecast.setPressure(Objects.requireNonNull(fcr).getPressure());
        return forecast;
    }

    public Forecast getForecast(ObjectMapper objectMapper, Double lat, Double lon) {
        Forecast forecast;
        StringBuilder uri = new StringBuilder("https://api.openweathermap.org/data/2.5/onecall");
        uri.append("?lat=");
        uri.append(lat);
        uri.append("&lon=");
        uri.append(lon);
        uri.append("&appid=");
        uri.append(API_KEY);
        uri.append("&exclude=daily,minutely,hourly,alerts");
        uri.append("&units=metric");
        try {
            // create a new http client
            HttpClient httpClient = HttpClient.newHttpClient();
            // prepare a http request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri.toString()))
                    .build();

            // send a http request
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            // get a body from the http response
            String body = httpResponse.body();
            // deserialize JSON -> TimeDTO
            ForecastClientResponse forecastClientResponse =
                    objectMapper.readValue(body, ForecastClientResponse.class);
            if (forecastClientResponse == null) {
                throw new RuntimeException("Invalid JSON.");
            }
            // TODO: get location from user information
            ForecastClientResponse.ForecastCurrent fcr = forecastClientResponse.getCurrent();
            forecast = mapToForecast(fcr);
            return forecast;
        } catch (Exception e) {
            throw new RuntimeException("Connection issues: " + e.getMessage());
        }
    }

}
