package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealFeelTemperature {

    @SerializedName("Metric")
    @Expose
    private com.example.android.weatherly.Metric Metric;

    public com.example.android.weatherly.Metric getMetric() {
        return Metric;
    }

    public void setMetric(com.example.android.weatherly.Metric metric) {
        Metric = metric;
    }
}
