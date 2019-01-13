package com.travelg.ClusteringAlgorith;

import com.travelg.Model.Sight;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface SightClient {

    @GET("/cities/{city}/sights")
    Call<List<Sight>> repoForSights(@Path("city") String city);
}
