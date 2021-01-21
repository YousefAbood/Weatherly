package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speed {

    @SerializedName("Metric")
    @Expose
    private Metric Metric;


    public com.example.android.weatherly.Metric getMetric() {
        return Metric;
    }
}
