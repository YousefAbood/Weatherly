package com.example.android.weatherly.data.model.Forecast;

import com.google.gson.annotations.SerializedName;

public class HourItem{

	@SerializedName("condition")
	private Condition condition;

	public Condition getCondition(){
		return condition;
	}
}