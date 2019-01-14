package com.travelg.Model;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name="photos")
public class Photo {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idPhoto;

    private String name;

    private double longitude;

    private double latitude;

    private String imageUrl;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="sightId",referencedColumnName = "sightId")
    private Sight sight;

    public Photo(){}
    public UUID getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(UUID idPhoto) {
        this.idPhoto = idPhoto;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Sight getSight() {
        return sight;
    }

    public void setSight(Sight sight) {
        this.sight = sight;
    }

    public Photo(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Photo(String name, double longitude, double latitude,Sight sight) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sight = sight;
    }

}
