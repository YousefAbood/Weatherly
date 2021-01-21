package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class locationCountry {

    @SerializedName("LocalizedName")
    @Expose
    private String countryName;

    public String getCountryName() {
        return countryName;
    }
}
