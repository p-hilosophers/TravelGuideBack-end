package com.example.travelg.Controller;

import com.example.travelg.Model.CityData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController

public class CityDataController {
    private List<CityData> cityDataList = new ArrayList<>();
    @PostMapping("/cityData")
    public void addCityData(@Valid @RequestBody CityData cityData)
    {
        cityDataList.add(cityData);
        System.out.println(cityData.getCityName());
    }
}
