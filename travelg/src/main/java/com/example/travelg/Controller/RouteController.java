package com.example.travelg.Controller;

import com.example.travelg.ClusteringAlgorith.ClusteringResult;
import com.example.travelg.ClusteringAlgorith.ClusteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RouteController {

    @Autowired
    ClusteringService service;

    @GetMapping("/routes/{cityName}")
    public ClusteringResult getRoutes(@PathVariable String cityName) throws IOException
    {
        return service.getRoutes(cityName);
    }
}
