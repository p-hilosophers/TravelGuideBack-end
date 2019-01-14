package com.travelg;

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
public class JsonDataParser {

    private List<String> distanceInKm = new ArrayList<>(),durationInMin = new ArrayList<>();
    private List<Double> destinationsLat = new ArrayList<>(), destinationsLon = new ArrayList<>();
    private List<SightDto> list = new ArrayList<>();
    private List<Sight> sights = new ArrayList<>();

@GetMapping("/calculateDistance/{originsLat}/{originsLon}/{cityName}")
    public List<SightDto> dataParsing(@PathVariable double originsLat,@PathVariable double originsLon, @PathVariable String cityName) throws IOException {
        list.clear();
        sights.clear();
        destinationsLat.clear();
        destinationsLon.clear();
        distanceInKm.clear();
        durationInMin.clear();

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
                distanceInKm.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getString("text"));
                durationInMin.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("duration").getString("text"));
                sDto.setName(obj.getJSONArray("destination_addresses").get(i).toString());
                sDto.setDistance(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getDouble("value"));
                list.add(sDto);
           }
        }

    list.sort(Comparator.comparing(SightDto::getDistance));
    return list;
    }

}
