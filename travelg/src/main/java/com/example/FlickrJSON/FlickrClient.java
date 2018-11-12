package com.example.FlickrJSON;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FlickrClient {

    @GET("/services/rest/")
    Call<JsonObject> getPlaceId(@Query("method") String method
            ,@Query("api_key") String apiKey
            ,@Query("format") String format
            ,@Query("nojsoncallback") String nojsoncallback
            ,@Query("query") String query);

    @GET("/services/rest/")
    Call<JsonObject> getTopPhotoCountPlace(@Query("method") String method
            ,@Query("api_key") String apiKey
            ,@Query("format") String format
            ,@Query("nojsoncallback") String nojsoncallback
            ,@Query("place_id") String placeId);

    @GET("/services/rest/")
    Call<JsonObject> getPhotos(@Query("method") String method
            ,@Query("api_key") String apiKey
            ,@Query("format") String format
            ,@Query("nojsoncallback") String nojsoncallback
            ,@Query("lat") String lat
            ,@Query("lon") String lon
            ,@Query("radius") String radius);

    @GET("/services/rest/")
    Call<JsonObject> getGeoLocOfPhoto(@Query("method") String method
            ,@Query("api_key") String apiKey
            ,@Query("format") String format
            ,@Query("nojsoncallback") String nojsoncallback
            ,@Query("photo_id") String photoId);


    @GET("/cities")
    Call<JsonObject> getCities();

    /*@POST("/cities/")
    Call<JsonObject> putCity(@Body City city);

    @POST("cities/{cityId}")
    Call<JsonObject> putPhoto(@Body Photo photo);*/



}

