package com.example.promogela;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Store implements Serializable {

    private String name;

    private LatLng latLng;

    private String description;

    private String address;

    public Store(String name, LatLng latLng, String description, String rAddress) {
        this.name = name;
        this.latLng = latLng;
        this.description = description;
        this.address = rAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
