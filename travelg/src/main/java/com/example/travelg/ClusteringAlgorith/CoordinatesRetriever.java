package com.example.travelg.ClusteringAlgorith;

import com.example.travelg.Model.Sight;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoordinatesRetriever {

    private List<Sight> sights = new ArrayList<>();

    public List<Sight> retrieveSights(String city)
    {
        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://83.212.103.26:8080/")
            .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        call.enqueue(new Callback<List<Sight>>() {
            @Override
            public void onResponse(Call<List<Sight>> call, Response<List<Sight>> response) {
                String text = "";
                sights = response.body();
                for(Sight sight:sights)
                {
                    text += sight.getLatitude()+","+sight.getLongitude()+","+sight.getName()+"\n";
                }
                FileCreator fileCreator = new FileCreator();
                try {
                    fileCreator.createFile(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Sight>> call, Throwable throwable) {
                System.out.println("error");
            }

        });
        return sights;
    }
}

