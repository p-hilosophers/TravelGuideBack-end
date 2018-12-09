package com.example.travelg.ClusteringAlgorith;

import com.example.travelg.Model.Sight;

import java.util.ArrayList;
import java.util.List;

public class ClusteringResult {

    private List<List<Sight>> routes = new ArrayList<>();

    public ClusteringResult() {}

    public ClusteringResult(List<List<Sight>> routes)
    {
        this.routes = routes;
    }

    public boolean isEmpty()
    {
        return  routes.isEmpty();
    }

    public List<List<Sight>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<Sight>> routes) {
        this.routes = routes;
    }
}
