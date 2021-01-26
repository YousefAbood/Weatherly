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

    @SerializedName("code")
    @Expose
    public int code;

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getImageIconURL() {
        String imageIconImproved = "https:" + imageIconURL;
        return imageIconImproved;
    }

    public String getCode() {
        String codeString = String.valueOf(code);
        return codeString;
    }
}
