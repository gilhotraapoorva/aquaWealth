package com.aquawealth.util;

import com.aquawealth.model.WeatherRecord;
import com.aquawealth.repository.WeatherRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service //  Make it a Spring-managed bean
public class WeatherAPIUtil {

    private static final String API_KEY = "5abf2fcd673d4c8f8f085825251103";
    private static final String CURRENT_WEATHER_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private static final String HISTORICAL_WEATHER_URL = "http://api.weatherapi.com/v1/history.json?key=";
    private final String[] cities = {
            "New York", "London", "Buenos Aires", "Tokyo", "Mumbai", "Sydney",
            "Dubai", "Moscow", "Cape Town", "Toronto", "Los Angeles"
    };

    public String checkWeatherRisk(String city, String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = HISTORICAL_WEATHER_URL + API_KEY + "&q=" + city + "&dt=" + date;

        try {
            System.out.println("Fetching weather for: " + city + " | Date: " + date);
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);

            double precipitation = json.getJSONObject("forecast").getJSONArray("forecastday")
                    .getJSONObject(0).getJSONObject("day")
                    .getDouble("totalprecip_mm");


            System.out.println("Total Precipitation: " + precipitation + " mm");

            if (precipitation > 12) {
                return "Flood Risk Detected!";
            } else if (precipitation < 0.5) {
                return " Drought Risk Detected!";
            } else {
                return " Normal Weather.";
            }

        } catch (Exception e) {
            return " Error fetching weather data: " + e.getMessage();
        }
    }

    public List<String> getExtremeWeatherCities() {
        RestTemplate restTemplate = new RestTemplate();
        List<String> extremeWeatherCities = new ArrayList<>();

        for (String city : cities) {
            try {
                String url = HISTORICAL_WEATHER_URL + API_KEY + "&q=" + city;
                String response = restTemplate.getForObject(url, String.class);
                JSONObject json = new JSONObject(response);

                double temp = json.getJSONObject("current").getDouble("temp_c");
                double windSpeed = json.getJSONObject("current").getDouble("wind_kph");
                double rainfall = json.getJSONObject("current").getDouble("precip_mm");

                if (temp > 50 || temp < -30 || windSpeed > 100 || rainfall > 100) {
                    extremeWeatherCities.add(city);
                }
            } catch (Exception e) {
                System.out.println("Error fetching data for: " + city);
            }
        }
        return extremeWeatherCities;
    }

    @Autowired
    private WeatherRecordRepository weatherRecordRepository;  //  Inject Repository

    public String getWeather(String city, String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = HISTORICAL_WEATHER_URL + API_KEY + "&q=" + city + "&dt=" + date;

        try {
            System.out.println("Fetching weather for: " + city + " on " + date);
            System.out.println(" API Request URL: " + url);

            String response = restTemplate.getForObject(url, String.class);
            System.out.println(" API Response: " + response);

            JSONObject json = new JSONObject(response);

            if (json.has("error")) {
                return " API Error: " + json.getJSONObject("error").getString("message");
            }

            if (!json.has("forecast")) {
                return " Error: No forecast data found!";
            }

            double precipitation = json.getJSONObject("forecast").getJSONArray("forecastday")
                    .getJSONObject(0).getJSONObject("day")
                    .getDouble("totalprecip_mm");

            System.out.println("Extracted Precipitation: " + precipitation + " mm");

            if (precipitation > 12) {
                return "️ Flood Risk Detected!";
            } else if (precipitation < 0.5) {
                return "Drought Risk Detected!";
            } else {
                return "Normal Weather.";
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "Error fetching weather data: " + e.getMessage();
        }
    }

    public void fetchAndStoreWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();

        for (int i = 1; i <= 15; i++) {
            LocalDate date = today.minusDays(i);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String url = HISTORICAL_WEATHER_URL + API_KEY + "&q=" + city + "&dt=" + formattedDate;

            try {
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("Weather API Response: " + response);
                JSONObject json = new JSONObject(response);
                JSONObject forecast = json.getJSONObject("forecast");
                JSONObject forecastDay = forecast.getJSONArray("forecastday").getJSONObject(0);
                JSONObject day = forecastDay.getJSONObject("day");
                String condition = day.getJSONObject("condition").getString("text");

                String conditionType = "Normal";
                if (condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("storm")) {
                    conditionType = "Flood";
                } else if (condition.toLowerCase().contains("dry") || condition.toLowerCase().contains("heat")) {
                    conditionType = "Drought";
                }

                WeatherRecord record = new WeatherRecord();
                record.setCity(city);
                record.setCondition(condition);
                record.setConditionType(conditionType);
                record.setDate(date);

                weatherRecordRepository.save(record);
            } catch (Exception e) {
                System.out.println("Error fetching weather data for date " + formattedDate + ": " + e.getMessage());
            }
        }
    }
}


