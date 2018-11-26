package com.example.travelg.Repository;

import com.example.travelg.Model.Sight;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface SightClient {

    @GET("/cities/{cityName}/sights")
    Call<List<Sight>> repoForSights(@Path("cityName") String city);
}
