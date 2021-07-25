package com.example.android.weatherly.data.model.CurrentWeatherList;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherList{

	@SerializedName("current")
	private Current current;

	@SerializedName("location")
	private Location location;

	public Current getCurrent(){
		return current;
	}

	public Location getLocation(){
		return location;
	}
}