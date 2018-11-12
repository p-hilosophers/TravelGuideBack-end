package com.example.FlickrJSON;

import java.util.ArrayList;
import java.util.List;

public class FlickrInfoSearch {

    private FlickrHttpRequest flickrHttpRequest = new FlickrHttpRequest();

    public void getPhotosForPlace(String placeName){
        List<PhotoGeoLoc> photoGeoLocList = new ArrayList<>();
        String placeIdRequest = flickrHttpRequest.placeIdRequest(placeName);
        RegionGeoLoc regionGeoLoc = flickrHttpRequest.topCountPhotoPlaceRequest(placeIdRequest);
        List<String> idList = flickrHttpRequest.photoSearch(regionGeoLoc.getLatitude(), regionGeoLoc.getLongitude());
        for(String id : idList) {
            photoGeoLocList.add(flickrHttpRequest.geoLocOfPhoto(id));
        }
        for(PhotoGeoLoc photoGeoLoc : photoGeoLocList){
            System.out.println("lat : " +photoGeoLoc.getLatitude() + " lon : " + photoGeoLoc.getLongitude());
        }
    }
}
