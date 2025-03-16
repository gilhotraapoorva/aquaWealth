package com.aquawealth.service;

import com.aquawealth.repository.WeatherRecordRepository;
import org.springframework.stereotype.Service;
import com.aquawealth.util.WeatherAPIUtil;

@Service
public class WeatherService {

    private final WeatherAPIUtil weatherAPIUtil;

    public WeatherService(WeatherAPIUtil weatherAPIUtil) {
        this.weatherAPIUtil = weatherAPIUtil;
    }

    public void updateWeatherForCity(String city) {
        weatherAPIUtil.fetchAndStoreWeather(city);
    }
}
