package com.maps.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Объект для отображения данных на карте
 * Использую Serializable вместо Parcelable
 * потому что не нужно объект передавать из одного активити в другой
 */
public class Shop implements Serializable {

    private String name;
    private Bitmap image;
    private double latitude;
    private double longitude;

    public Shop() {}

    public Shop(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Shop(String name, double latitude, double longitude, Bitmap image) {
        this.name = name;
        this.image = image;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return getName();
    }

}
