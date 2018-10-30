package com.example.FlickrJSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrRequest {


    private JSONObject responseJSON_Format;
    private final String apiKey = "967c78a908c3f74879ddf240240f35fa";
    private final String authToken = "72157701468768951-06ac99cf366773a6";
    private final String apiSig = "7775f3a56ce7cf12bfcd6aa705dbba37";

    public void getPhotosByGeoLoc(String latitude , String longitude,String radius){

        try{

            String url ="https://api.flickr.com/services/rest/?method=flickr.photos.search"+
                    "&api_key=" + apiKey +
                    "&lat=" + latitude +
                    "&lon=" + longitude +
                    "&radius=" + radius +
                    "&radius_units=km&format=json&nojsoncallback=1&auth_token=" + authToken+
                    "&api_sig=" + apiSig;

            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + String.valueOf(responseCode));

            BufferedReader in = new BufferedReader( new InputStreamReader((con.getInputStream())));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

            responseJSON_Format = new JSONObject(response.toString());

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    public JSONObject getResponseJSON_Format() {
        return responseJSON_Format;
    }

    public void setResponseJSON_Format(JSONObject responseJSON_Format) {
        this.responseJSON_Format = responseJSON_Format;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getApiSig() {
        return apiSig;
    }

}
