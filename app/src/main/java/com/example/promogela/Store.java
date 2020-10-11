package com.example.promogela;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Store implements Serializable {

    private String name;

    private LatLng latLng;

    public Store(String name, LatLng latLng) {
        this.name = name;
        this.latLng = latLng;
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
