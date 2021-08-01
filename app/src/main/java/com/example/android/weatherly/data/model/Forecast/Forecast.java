package com.example.android.weatherly.data.model.Forecast;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Forecast{

	@SerializedName("forecastday")
	private List<ForecastdayItem> forecastday;

	public List<ForecastdayItem> getForecastday(){
		return forecastday;
	}
}