package com.example.android.weatherly.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class locationAPI {

    @SerializedName("name")
    @Expose
    public String cityName;

    @SerializedName("region")
    @Expose
    public String regionName;

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
        String cityAndCountry = cityName + ", " + regionName + ", " + countryName;
        return cityAndCountry;
    }
}
