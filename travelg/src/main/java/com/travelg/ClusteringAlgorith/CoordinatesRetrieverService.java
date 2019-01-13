package com.travelg.ClusteringAlgorith;

import com.travelg.Model.Sight;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoordinatesRetrieverService {

    private List<Sight> sights = new ArrayList<>();

    public List<Sight> retrieveSights(String city) throws IOException
    {
        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://35.204.237.100:8081/")
            .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        Response<List<Sight>> result = call.execute();
        String text = "";
        sights = result.body();
        for(Sight sight:sights) {
            text += sight.getLatitude()+","+sight.getLongitude()+","+sight.getName()+"\n";
        }
        FileCreator fileCreator = new FileCreator();
        try {
            fileCreator.createFile(text,"sights");
            text = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sights;
    }
}

