package com.example.android.weatherly.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class searchLocation {

    @SerializedName("id")
    @Expose
    public int searchLocationId;

    @SerializedName("name")
    @Expose
    public String searchLocationName;

    @SerializedName("region")
    @Expose
    public String searchLocationRegion;

    @SerializedName("country")
    @Expose
    public String searchLocationCountry;

    @SerializedName("lat")
    @Expose
    public double latitudeSearchLocation;

    @SerializedName("lon")
    @Expose
    public double longitudeSearchLocation;



    public int getSearchLocationId() {
        return searchLocationId;
    }

    public String getSearchLocationName() {
        return searchLocationName;
    }

    public String getSearchLocationRegion() {
        return searchLocationRegion;
    }

    public String getSearchLocationCountry() {
        return searchLocationCountry;
    }

    public String getLatitudeSearchLocation() {
        String slatitudeSearchLocation = "" + latitudeSearchLocation;
        return slatitudeSearchLocation;
    }

    public String getLongitudeSearchLocation() {
        String slongitudeSearchLocation = "" + longitudeSearchLocation;
        return slongitudeSearchLocation;
    }
}
