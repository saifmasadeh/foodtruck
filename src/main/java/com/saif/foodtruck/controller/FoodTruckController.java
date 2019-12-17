package com.saif.foodtruck.controller;


import com.saif.foodtruck.model.Truck;
import com.saif.foodtruck.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodTruckController {

    @Autowired
    private FoodTruckService foodTruckService;

    @GetMapping("/allTrucks")
    public String allTrucks(Model model) {
        Truck[] trucks;
        trucks = foodTruckService.allTrucks();
        model.addAttribute("trucks", trucks);
        return "trucks.html";
    }

    @GetMapping("/trucksAtTime/{startTime}")
    public String trucksAtTime(@PathVariable("startTime") String startTime, Model model) {
        Truck[] trucks;
        trucks = foodTruckService.trucksAtTime(startTime);
        model.addAttribute("trucks", trucks);
        return "trucks.html";
    }
}
