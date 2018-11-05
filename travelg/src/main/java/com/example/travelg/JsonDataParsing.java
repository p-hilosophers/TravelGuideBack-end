package com.example.travelg;

import org.json.JSONObject;

public class JsonDataParsing {
    String destAddresses,distanceInKm,durationInMin;
    public void DataParsing(double originslat, double originslon, double destinationslat, double destinationslon) {
        JSONObject obj = new JSONObject();
        obj = DistanceCalculation.CalcDistance(originslat, originslon, destinationslat, destinationslon);
        System.out.println(obj.toString());
        destAddresses = obj.getJSONArray("destination_addresses").get(0).toString();
        distanceInKm = obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
        durationInMin = obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getString("text");
        System.out.println(destAddresses + "\t" + distanceInKm + "\t" + durationInMin);
    }

    public String getDestAddresses() {
        return destAddresses;
    }

    public String getDistanceInKm() {
        return distanceInKm;
    }

    public String getDurationInMin() {
        return durationInMin;
    }
}
