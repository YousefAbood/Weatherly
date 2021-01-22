package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("text")
    @Expose
    public String weatherCondition;

    @SerializedName("icon")
    @Expose
    public String imageIconURL;

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getImageIconURL() {
        return imageIconURL;
    }
}
