package com.travelg.Model;

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

    private String photo;

    public City(){}

    public City(String name, double longitude, double latitude, String photo) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
    }

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

    public void setPhoto(String photo){this.photo = photo;}

    public String getPhoto(){return photo;};
}
