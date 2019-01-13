package com.travelg;

import com.travelg.ClusteringAlgorith.SightClient;
import com.travelg.Model.Sight;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SightDataRetriever {

    static int count = 0;

    public static int getCount() {
        return count;
    }

    public static List<Sight> returnData(String city) throws IOException {
        List<Sight> sightList = new ArrayList<>();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://35.204.237.100:8081/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);
        Response response = call.execute();
        List<Sight> repos = (List<Sight>) response.body();
        for(Sight sight: repos) {
            sightList.add(sight);
            count++;
        }
        return sightList;
    }
}
