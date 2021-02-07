package com.example.android.weatherly.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("forecastday")
    @Expose
    private List<forecastday> forecastdayList = null;

    public List<forecastday> getForecastdayList() {
        return forecastdayList;
    }

    public void setForecastdayList(List<forecastday> forecastdayList) {
        this.forecastdayList = forecastdayList;
    }
}
