package com.example.android.weatherly.data.model;

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

    @SerializedName("pressure_mb")
    @Expose
    public Double pressureMB;

    @SerializedName("precip_mm")
    @Expose
    public Double precipMM;

    @SerializedName("is_day")
    @Expose
    public int dayOrNight;

    @SerializedName("last_updated")
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
        String temperatureCel = temperatureCelcius + "°";
        return temperatureCel;
    }

    public String getTemperatureFahrenheit() {
        String temperatureCel = temperatureFahrenheit + "°";
        return temperatureCel;
    }

    public String getFeelsLikeCelcius() {
        String temperatureCel = "Feels like " + feelsLikeCelcius + "°";
        return temperatureCel;
    }

    public String getFeelsLikeFahrenheit() {
        String temperatureCel = "Feels like " + feelsLikeFahrenheit + "°";
        return temperatureCel;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getWindSpeedMPH() {
        String windSpeed = windSpeedMPH + " mph";
        return windSpeed;
    }

    public String getWindSpeedKPH() {
        String windSpeed = windSpeedMPH + " km/h";
        return windSpeed;
    }

    public String getHumidity() {
        String humidityString = humidity + "%";
        return humidityString;
    }

    public String getPressureMB() {
        int pressureMBInteger = (int) Math.round(pressureMB);
        String pressureMBString = pressureMBInteger + " hPa";
        return pressureMBString;
    }

    public String getPrecipMM() {
        Double precipMMDouble = precipMM * 100;
        int precipMMInteger = (int) Math.round(precipMMDouble);
        String precipMMString = precipMMInteger + "%";
        return precipMMString;
    }

    public String getDate() {
        return date;
    }
}
