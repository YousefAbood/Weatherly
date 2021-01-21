package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("LocalizedName")
    @Expose
    private String cityName;
    @SerializedName("Country")
    @Expose
    private locationCountry locationCountry;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public com.example.android.weatherly.locationCountry getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(com.example.android.weatherly.locationCountry locationCountry) {
        this.locationCountry = locationCountry;
    }
}
