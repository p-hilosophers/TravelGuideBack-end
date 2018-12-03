package com.example.travelg.ClusteringAlgorith;

import com.example.travelg.Model.Sight;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Clustering {

    @GetMapping("/routes/{cityName}")
    public List<List<Sight>> showRoutes(@PathVariable String cityName) throws IOException {
        CoordinatesRetriever retriever = new CoordinatesRetriever();
        retriever.retrieveSights(cityName);
        Dataset data = FileHandler.loadDataset(new File("sights.txt"), 2, ",");
        List<List<Sight>> sightList = new ArrayList<>();

        int k = (data.size()/3)+1;
        Clusterer km = new KMeans(k);
        Dataset[] clusters = km.cluster(data);

        for(int i = 0; i<clusters.length;i++)
        {
            List<Sight> sights = new ArrayList<>();
            for(int j = 0;j < clusters[i].size();j++)
            {
              Sight sight = new Sight();
                 sight.setName((String)clusters[i].get(j).classValue());
                 sight.setLatitude(clusters[i].get(j).get(0));
                 sight.setLongitude(clusters[i].get(j).get(1));
                sights.add(sight);
            }
            sightList.add(sights);
        }
        return sightList;
    }


}
