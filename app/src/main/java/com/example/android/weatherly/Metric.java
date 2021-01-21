package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metric {

    @SerializedName("Value")
    @Expose
    private Double value;

    @SerializedName("Unit")
    @Expose
    private String unit;

    public String getValue() {
        String StringValue = String.valueOf(value) + ' ' +  unit;
        return StringValue;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
