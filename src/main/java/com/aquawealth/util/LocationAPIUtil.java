package com.aquawealth.util;

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

public class LocationAPIUtil {
    private static final String GEOLOCATION_URL = "http://ip-api.com/json/";

    public static String getUserLocation() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(GEOLOCATION_URL, String.class);
        JSONObject json = new JSONObject(response);

        return json.optString("city", "Unknown") + ", " + json.optString("country", "Unknown");
    }
}
