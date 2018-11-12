package com.example.FlickrJSON;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private JsonObject jsonObject;

    public String getPlaceId(){
        return jsonObject.getAsJsonObject("places")
                .getAsJsonArray("place")
                .get(0).getAsJsonObject()
                .get("place_id")
                .getAsString();

    }

    public RegionGeoLoc getGeoLocOfRegion(){
        RegionGeoLoc regionGeoLoc = new RegionGeoLoc();
        regionGeoLoc.setLatitude(jsonObject.getAsJsonObject("places")
                .getAsJsonArray("place")
                .get(0).getAsJsonObject()
                .get("latitude").getAsString());

        regionGeoLoc.setLongitude(jsonObject.getAsJsonObject("places")
                .getAsJsonArray("place")
                .get(0).getAsJsonObject()
                .get("longitude").getAsString());

        return regionGeoLoc;
    }

    public List<String> getPhotosForPlace(){
        List<String> photoIdList= new ArrayList<>();
        for(int i = 0; i<25;i++) {
            photoIdList.add(String.valueOf(jsonObject.getAsJsonObject("photos").getAsJsonArray("photo").get(i).getAsJsonObject().get("id").getAsString()));
        }
        return photoIdList;
    }

    public PhotoGeoLoc getGeoLocOfPhoto(){
        return new PhotoGeoLoc(jsonObject.getAsJsonObject("photo").getAsJsonObject("location").get("latitude").getAsString(),
        jsonObject.getAsJsonObject("photo").getAsJsonObject("location").get("longitude").getAsString());
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
