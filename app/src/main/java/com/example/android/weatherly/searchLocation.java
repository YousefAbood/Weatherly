package com.example.android.weatherly;

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
}
