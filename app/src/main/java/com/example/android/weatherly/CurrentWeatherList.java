package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherList {

    // Current Weather Condition
    @SerializedName("WeatherText")
    @Expose
    private String currentWeatherCondition;

    // DayTime
    @SerializedName("IsDayTime")
    @Expose
    private Boolean dayOrNight;

    // Temperature
    @SerializedName("Temperature")
    @Expose
    private com.example.android.weatherly.Temperature temperature;

    // Weather Icon (For Photos)
    @SerializedName("WeatherIcon")
    @Expose
    private Integer WeatherIcon;

    // Real Feel Temperature
    @SerializedName("RealFeelTemperature")
    @Expose
    private RealFeelTemperature realFeelTemperature;

    // Relative Humidity
    @SerializedName("RelativeHumidity")
    @Expose
    private Integer humidity;

    // Wind Speed
    @SerializedName("Wind")
    @Expose
    private Wind wind;

    public String getCurrentWeatherCondition() {
        return currentWeatherCondition;
    }

    public void setCurrentWeatherCondition(String currentWeatherCondition) {
        this.currentWeatherCondition = currentWeatherCondition;
    }

    public Boolean getDayOrNight() {
        return dayOrNight;
    }

    public void setDayOrNight(Boolean dayOrNight) {
        this.dayOrNight = dayOrNight;
    }

    public com.example.android.weatherly.Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(com.example.android.weatherly.Temperature temperature) {
        this.temperature = temperature;
    }

    public Integer getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(Integer weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public RealFeelTemperature getRealFeelTemperature() {
        return realFeelTemperature;
    }

    public void setRealFeelTemperature(RealFeelTemperature realFeelTemperature) {
        this.realFeelTemperature = realFeelTemperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
