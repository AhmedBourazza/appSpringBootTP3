package com.example.tp3.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class geocodingService {

    private final RestTemplate restTemplate;

    public geocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String geocodeAddress(String address) {
        String apiUrl = "https://api-adresse.data.gouv.fr/search/?q=" + address;
        return restTemplate.getForObject(apiUrl, String.class);
    }
}