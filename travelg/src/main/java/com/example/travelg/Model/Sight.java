package com.example.travelg.Model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="sights")
public class Sight {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID sightId;

    private String name;

    private double longitude;

    private double latitude;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="cityId",referencedColumnName = "cityId")
    private City city;

    public Sight(){}

    public Sight(String name, double longitude, double latitude, City city) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public UUID getSightId() {
        return sightId;
    }

    public void setSightId(UUID sightId) {
        this.sightId = sightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
