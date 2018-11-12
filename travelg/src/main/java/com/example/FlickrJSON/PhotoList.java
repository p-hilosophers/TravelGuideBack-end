package com.example.FlickrJSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotoList {

    private List<String> photoIdList = new ArrayList<>();
    private FlickrRequest flickrRequest = new FlickrRequest();
    private JSONObject json = new JSONObject();

    public void placeIdFromJSON(String nameOfPlace){
        flickrRequest.getPlaceId(nameOfPlace);
        json = flickrRequest.getResponseJSON_Format();
        String placeId = json.getJSONObject("places").getJSONArray("place").getJSONObject(0).getString("place_id");
        getTopPhotoCountOfRegion(placeId);
    }

    private void getTopPhotoCountOfRegion(String placeId){
        flickrRequest.photoCountPerRegion(placeId);
        json = flickrRequest.getResponseJSON_Format();

        String latitude =json.getJSONObject("places").getJSONArray("place").getJSONObject(0).getString("latitude");
        String longitude =json.getJSONObject("places").getJSONArray("place").getJSONObject(0).getString("longitude");
        String photoCount = json.getJSONObject("places").getJSONArray("place").getJSONObject(0).getString("photo_count");

        getPhotoListByGeoLoc(latitude,longitude);
    }

    private void getPhotoListByGeoLoc(String latitude,String longitude){
        flickrRequest.getPhotosByGeoLoc(latitude,longitude,"2");
        json = flickrRequest.getResponseJSON_Format();

        for(int i =0 ; i<20;i++){
            photoIdList.add(json.getJSONObject("photos").getJSONArray("photo").getJSONObject(i).getString("id"));
        }
        getGeoLocFromPhotoId(photoIdList);
    }

    private void getGeoLocFromPhotoId(List<String> photoList){
        List<PhotoGeoLoc> photoGeoLocsList = new ArrayList<>();

        for(String id : photoList) {
            flickrRequest.geoLocFromPhoto(id);
            json = flickrRequest.getResponseJSON_Format();
            photoGeoLocsList.add(new PhotoGeoLoc(json.getJSONObject("photo").getJSONObject("location").getString("latitude").toString(),
                    json.getJSONObject("photo").getJSONObject("location").getString("longitude").toString()));
        }

        for(PhotoGeoLoc photoGeoLoc : photoGeoLocsList){
            System.out.println(photoGeoLoc.getLatitude() + " " + photoGeoLoc.getLongitude() + " " );
        }
    }

    public List<String> getPhotoIdList() {
        return photoIdList;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
}
