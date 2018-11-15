package com.example.travelg.Model;


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

    private byte[] image;

    private byte[] thumbnail;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
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
}
