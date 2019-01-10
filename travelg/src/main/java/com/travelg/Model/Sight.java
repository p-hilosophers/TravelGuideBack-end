package com.travelg.Model;

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

    private String season;

    private String dayNight;

    private int photoCount;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="cityId",referencedColumnName = "cityId")
    private City city;

    public Sight(){}

    public Sight(String name, double longitude, double latitude, String season, String dayNight, int photoCount, City city) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.season = season;
        this.dayNight = dayNight;
        this.photoCount = photoCount;
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

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDayNight() {
        return dayNight;
    }

    public void setDayNight(String dayNight) {
        this.dayNight = dayNight;
    }
}
