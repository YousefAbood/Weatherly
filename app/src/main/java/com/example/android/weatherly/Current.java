package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("temp_c")
    @Expose
    public double temperatureCelcius;

    @SerializedName("temp_f")
    @Expose
    public double temperatureFahrenheit;

    @SerializedName("feelslike_c")
    @Expose
    public double feelsLikeCelcius;

    @SerializedName("feelslike_f")
    @Expose
    public double feelsLikeFahrenheit;

    @SerializedName("condition")
    @Expose
    public Condition condition;

    @SerializedName("wind_mph")
    @Expose
    public double windSpeedMPH;

    @SerializedName("wind_kph")
    @Expose
    public double windSpeedKPH;

    @SerializedName("humidity")
    @Expose
    public int humidity;

    @SerializedName("is_day")
    @Expose
    public int dayOrNight;

    @SerializedName("date")
    @Expose
    public String date;


    public String getDayOrNight() {
        String sDayOrNight;
        if(dayOrNight == 1) {
            sDayOrNight = "Day";
        } else {
            sDayOrNight = "Night";
        }
        return sDayOrNight;
    }

    public String getTemperatureCelcius() {
        String temperatureCel = String.valueOf(temperatureCelcius) + " °C";
        return temperatureCel;
    }

    public String getTemperatureFahrenheit() {
        String temperatureCel = String.valueOf(temperatureFahrenheit) + " °F";
        return temperatureCel;
    }

    public String getFeelsLikeCelcius() {
        String temperatureCel = "Feels like " + String.valueOf(feelsLikeCelcius) + " °C";
        return temperatureCel;
    }

    public String getFeelsLikeFahrenheit() {
        String temperatureCel = "Feels like " + String.valueOf(feelsLikeFahrenheit) + " °F";
        return temperatureCel;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getWindSpeedMPH() {
        String windSpeed = String.valueOf(windSpeedMPH) + " mph";
        return windSpeed;
    }

    public String getWindSpeedKPH() {
        String windSpeed = String.valueOf(windSpeedMPH) + " km/h";
        return windSpeed;
    }

    public String getHumidity() {
        String humidityString = String.valueOf(humidity) + "%";
        return humidityString;
    }
}
