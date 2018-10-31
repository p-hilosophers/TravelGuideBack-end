package com.example.travelg.Model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name="cities")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID cityId;

    private String name;

    private double longitude;

    private double latitude;

    private int photoCount;

    public City(){}
    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
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

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }
}
