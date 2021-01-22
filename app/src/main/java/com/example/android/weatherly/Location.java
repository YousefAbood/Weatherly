package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("name")
    @Expose
    public String cityName;

    @SerializedName("country")
    @Expose
    public String countryName;

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityAndCountryName() {
        String cityAndCountry = cityName + ", " + countryName;
        return cityAndCountry;
    }
}
