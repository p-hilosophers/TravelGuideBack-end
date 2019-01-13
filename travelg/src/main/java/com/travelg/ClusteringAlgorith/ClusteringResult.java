package com.travelg.ClusteringAlgorith;

import com.travelg.Model.Sight;

import java.util.ArrayList;
import java.util.List;

public class ClusteringResult {

    private List<List<Sight>> routes = new ArrayList<>();

    public ClusteringResult(List<List<Sight>> routes)
    {
        this.routes = routes;
    }

    public List<List<Sight>> getRoutes() {
        return routes;
    }
}
