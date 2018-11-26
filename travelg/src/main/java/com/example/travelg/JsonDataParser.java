package com.example.travelg;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataParser {
    private List<String> destAddresses = new ArrayList<>(),distanceInKm = new ArrayList<>(),durationInMin = new ArrayList<>();
    private List<Double> distanceValue = new ArrayList<>();
    public void dataParsing(double originsLat, double originsLon, List<Double> destinationsLat, List<Double> destinationsLon) {
        JSONObject obj ;
        obj = DistanceCalculator.calcDistance(originsLat, originsLon, destinationsLat, destinationsLon);
        System.out.println(obj.toString());
        for(int i = 0; i< SightDataRetriever.getCount(); i++) {
            destAddresses.add(obj.getJSONArray("destination_addresses").get(i).toString());
            distanceInKm.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getString("text"));
            distanceValue.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("distance").getDouble("value"));
            durationInMin.add(obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(i).getJSONObject("duration").getString("text"));
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
