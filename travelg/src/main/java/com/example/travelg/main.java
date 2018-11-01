package com.example.travelg;

public class main {
    public static void main(String[] args) {
        String destinationAddresses,distanceInKm,durationInMin;
        JsonDataParsing jdp = new JsonDataParsing();
        jdp.DataParsing(51.509865,-0.118092,51.5003646652, -0.1214328476);
        destinationAddresses = jdp.getDestAddresses();
        distanceInKm = jdp.getDistanceInKm();
        durationInMin = jdp.getDurationInMin();
        System.out.println(destinationAddresses + "\t" + distanceInKm + "\t" + durationInMin);
    }
}
