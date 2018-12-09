package com.example.travelg.ClusteringAlgorith;

import com.example.travelg.Model.Sight;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.DistanceMeasure;
import net.sf.javaml.distance.ManhattanDistance;
import net.sf.javaml.tools.data.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClusteringService {

    @Autowired
    CoordinatesRetrieverService retriever;

    private Dataset data;
    private Dataset[] clusters;

    public ClusteringResult getRoutes(String cityName) throws IOException {

        setUp(cityName);
        List<List<Sight>> sightList = new ArrayList<>();
        if(clusters != null)
        {
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
        }
        return new ClusteringResult(sightList);
    }

    public void setUp(String cityName) throws IOException
    {
        retriever.retrieveSights(cityName);
        data = FileHandler.loadDataset(new File("sights.txt"), 2, ",");
        clusterSights();

    }

    public void clusterSights()
    {
        int k = (data.size()/3)+1;
        DistanceMeasure dm = new ManhattanDistance();
        Clusterer km = new KMeans(k,10,dm);
        if (!data.isEmpty())
            clusters = km.cluster(data);

    }



}
