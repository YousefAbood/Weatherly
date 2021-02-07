package com.example.android.weatherly.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class forecastday {

    @SerializedName("day")
    @Expose
    private Day day;

    @SerializedName("astro")
    @Expose
    private Astro astro;

    @SerializedName("date")
    @Expose
    private String date;


    public Day getDay() {
        return day;
    }

    public Astro getAstro() {
        return astro;
    }

    public String getDate() {
        return date;
    }
}
