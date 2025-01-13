package com.example.tp3.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MeteoController {
    @Value("${meteoconcept.api.key}")
    private String meteoconceptApiKey;
    private static final String ETALAB_API_URL = "https://api-adresse.data.gouv.fr/search/?q=";

    private final RestTemplate restTemplate;

    @Autowired
    public MeteoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @PostMapping("/meteo")
    public String getWeather(@RequestParam("address") String address, Model model) {
        try {
            String apiUrlEtalab = ETALAB_API_URL + address;
            String responseEtalab = restTemplate.getForObject(apiUrlEtalab, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseEtalab);
            JsonNode firstFeature = root.path("features").get(0).path("geometry").path("coordinates");

            double longitude = firstFeature.get(0).asDouble();
            double latitude = firstFeature.get(1).asDouble();


            String apiUrlMeteo = "https://api.meteo-concept.com/api/forecast/daily/0?latlng=" +
                    latitude + "," + longitude + "&token=" + meteoconceptApiKey;


            String responseMeteo = restTemplate.getForObject(apiUrlMeteo, String.class);

            JsonNode weatherRoot = objectMapper.readTree(responseMeteo);
            JsonNode city = weatherRoot.path("city");
            JsonNode forecast = weatherRoot.path("forecast");

            String cityName = city.path("name").asText();
            double cityLatitude = city.path("latitude").asDouble();
            double cityLongitude = city.path("longitude").asDouble();
            int tmin = forecast.path("tmin").asInt();
            int tmax = forecast.path("tmax").asInt();
            int windSpeed = forecast.path("wind10m").asInt();
            int windGust = forecast.path("gust10m").asInt();
            int weather = forecast.path("weather").asInt();
            double rain = forecast.path("rr10").asDouble();

            model.addAttribute("address", address);
            model.addAttribute("longitude", longitude);
            model.addAttribute("latitude", latitude);
            model.addAttribute("cityName", cityName);
            model.addAttribute("cityLatitude", cityLatitude);
            model.addAttribute("cityLongitude", cityLongitude);
            model.addAttribute("tmin", tmin);
            model.addAttribute("tmax", tmax);
            model.addAttribute("windSpeed", windSpeed);
            model.addAttribute("windGust", windGust);
            model.addAttribute("weather", weather);
            model.addAttribute("rain", rain);

            return "meteo";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            return "adresse";
        }
    }

}
