package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetForecast {

    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }
}
