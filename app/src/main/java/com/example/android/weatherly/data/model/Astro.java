package com.example.android.weatherly.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Astro {

    @SerializedName("sunrise")
    @Expose
    private String sunrise;

    @SerializedName("sunset")
    @Expose
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
