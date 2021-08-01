package com.example.android.weatherly.data.model.Forecast;

import com.google.gson.annotations.SerializedName;

public class GetForecast{

	@SerializedName("current")
	private Current current;

	@SerializedName("location")
	private Location location;

	@SerializedName("forecast")
	private Forecast forecast;

	public Current getCurrent(){
		return current;
	}

	public Location getLocation(){
		return location;
	}

	public Forecast getForecast(){
		return forecast;
	}
}