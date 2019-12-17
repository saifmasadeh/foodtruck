package com.saif.foodtruck.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saif.foodtruck.model.Truck;
import com.saif.foodtruck.model.Trucks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;

@Service
public class FoodTruckService {

    @Autowired
    private ObjectMapper objectMapper;

    private final String limit = "$limit:10";
    private final String order = "$order:starttime";

    @Autowired
    private RestTemplate restTemplate;

    private final String TRUCK_URL = "https://data.sfgov.org/resource/jjew-r69b.json";

    public Truck[] allTrucks() {
        ResponseEntity<Truck[]> trucks = restTemplate.getForEntity(TRUCK_URL, Truck[].class);
        return trucks.getBody();
    }

    public Truck[] trucksAtTime(String startTime) {
        String url = queryGen(TRUCK_URL, "starttime='"+startTime+"'", "dayorder","50");
        System.out.println(" url : "+ url);
        ResponseEntity<Truck[]> trucks = restTemplate.getForEntity(url, Truck[].class);
        return trucks.getBody();
    }

    private String queryGen(String url, String where, String order, String limit){
       return String.format("%s?$where=%s&$order=%s&$limit=%s",url, where,order, limit);
    }
}
