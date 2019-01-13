package com.example.travelg;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DistanceCalculator {

    public static JSONObject calcDistance(double originsLat, double originsLon, List<Double> destinationsLat, List<Double> destinationsLon){
        JSONObject jsonObj = null;
        try {
            String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?mode=walking" +
                    "&origins=" + originsLat + "," + originsLon +
                    "&destinations=" + destinationsLat.get(0) + "," + destinationsLon.get(0);

            for(int i = 1; i< SightDataRetriever.getCount(); i++) {
                urlString += "|" + destinationsLat.get(i) + "," + destinationsLon.get(i);
            }
            urlString += "&key=AIzaSyDn7h1CXfwVII-b4hMigDVQIe_5Kdrz6hQ";
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader((con.getInputStream())));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            jsonObj = new JSONObject(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
