package com.example.FlickrJSON;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlickrHttpRequest {

    private JsonParser jsonParser;
    private final String apiKey = "90a1ec87216b294a03bf2ca693ab9255";
    private final String responseFormat = "json";
    private final String url = "https://api.flickr.com/";
    private String method = "";
    private FlickrClient flickrClient;


    public void setMethod(String method){
        this.method = method;
                //"flickr.places.find";

    }

    public FlickrHttpRequest() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        flickrClient = retrofit.create(FlickrClient.class);
    }

    public String placeIdRequest(String place) {
        method = "flickr.places.find";
        Call<JsonObject> caller = flickrClient.getPlaceId(method, apiKey, responseFormat, "1", place);
        execute(caller);
        return jsonParser.getPlaceId();

    }

    public RegionGeoLoc topCountPhotoPlaceRequest(String region){
        method = "flickr.places.getChildrenWithPhotosPublic";
        Call<JsonObject> caller = flickrClient.getTopPhotoCountPlace(method, apiKey, responseFormat, "1", region);
        execute(caller);
        return jsonParser.getGeoLocOfRegion();
    }

    public List<String> photoSearch(String lat , String lon){
        method = "flickr.photos.search";
        Call<JsonObject> caller = flickrClient.getPhotos(method, apiKey, responseFormat, "1", lat,lon,"2");
        execute(caller);

        return jsonParser.getPhotosForPlace();
    }

    public PhotoGeoLoc geoLocOfPhoto(String photoId){
        method = "flickr.photos.getInfo";
        Call<JsonObject> caller = flickrClient.getGeoLocOfPhoto(method, apiKey, responseFormat, "1",photoId);
        execute(caller);
        return jsonParser.getGeoLocOfPhoto();
    }

    private void execute(Call<JsonObject> caller){
        jsonParser = new JsonParser();
        caller.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                jsonParser.setJsonObject(response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                System.out.println("error");
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
