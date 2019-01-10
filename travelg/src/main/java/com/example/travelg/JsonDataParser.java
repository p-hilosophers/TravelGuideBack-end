package com.example.travelg;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonDataParser {

    private List<String> destAddresses = new ArrayList<>(),distanceInKm = new ArrayList<>(),durationInMin = new ArrayList<>();
    private List<Double> distanceValue = new ArrayList<>();
    private List<SightDto> list = new ArrayList<>();

@GetMapping("/sortByDistance/{cityName}")
    public void dataParsing(double originsLat, double originsLon, List<Double> destinationsLat, List<Double> destinationsLon) {
        JSONObject obj ;
        obj = DistanceCalculator.calcDistance(originsLat, originsLon, destinationsLat, destinationsLon);
        System.out.println(obj.toString());
        for(int i = 0; i< SightDataRetriever.getCount(); i++) {
            SightDto sDto = new SightDto();
           if((obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getString("status")).equals("OK")) {
                distanceInKm.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getString("text"));
                durationInMin.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("duration").getString("text"));
                sDto.setName(obj.getJSONArray("destination_addresses").get(i).toString());
                sDto.setDistance(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getDouble("value"));
                list.add(sDto);
           }
        }
    }

    public List<String> getDestAddresses() {
        return destAddresses;
    }

    public List<String> getDistanceInKm() {
        return distanceInKm;
    }

    public List<String> getDurationInMin() {
        return durationInMin;
    }

    public List<Double> getDistanceValue() {
        return distanceValue;
    }
}
