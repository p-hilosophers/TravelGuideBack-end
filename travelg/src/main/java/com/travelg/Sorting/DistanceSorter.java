package com.travelg.Sorting;

import com.travelg.Model.Sight;
import org.json.JSONObject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class DistanceSorter {

@GetMapping("/distanceSort/{originsLat}/{originsLon}/{cityName}")
    public List<SightDto> sortByDistance(@PathVariable double originsLat,@PathVariable double originsLon, @PathVariable String cityName) throws IOException {
        List<SightDto> sightDtoList = new ArrayList<>();
        List<Sight> sights;
        List<Double> destinationsLat = new ArrayList<>(), destinationsLon = new ArrayList<>();

        sights = SightDataRetriever.returnData(cityName);
        for (int i = 0; i < SightDataRetriever.getCount(); i++) {
            destinationsLat.add(sights.get(i).getLatitude());
            destinationsLon.add(sights.get(i).getLongitude());
        }

        JSONObject obj ;
        obj = DistanceCalculator.calcDistance(originsLat, originsLon, destinationsLat, destinationsLon);

        for(int i = 0; i< SightDataRetriever.getCount(); i++) {
            SightDto sDto = new SightDto();
           if((obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getString("status")).equals("OK")) {
                sDto.setName(sights.get(i).getName());
                sDto.setDistance(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getDouble("value"));
                sightDtoList.add(sDto);
           }
        }

    sightDtoList.sort(Comparator.comparing(SightDto::getDistance));
    return sightDtoList;
    }



}
