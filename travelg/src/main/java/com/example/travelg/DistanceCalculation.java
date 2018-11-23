package com.example.travelg;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceCalculation {
    public static JSONObject CalcDistance (double originslat, double originslon, double destinationslat, double destinationslon){
        JSONObject jsonObj = null;
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?mode=walking&origins="+originslat+","+originslon+"&destinations="+destinationslat+","+destinationslon+"&key=AIzaSyDn7h1CXfwVII-b4hMigDVQIe_5Kdrz6hQ");
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
