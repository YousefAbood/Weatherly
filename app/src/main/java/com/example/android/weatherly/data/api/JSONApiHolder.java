package com.example.android.weatherly.data.api;

import com.example.android.weatherly.data.model.CurrentWeatherList.CurrentWeatherList;
import com.example.android.weatherly.data.model.Forecast.GetForecast;
import com.example.android.weatherly.data.model.searchLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONApiHolder {


    @GET("v1/forecast.json?key=2fc725cddfc84683bc9160939212101")
    Call<CurrentWeatherList> getCurrentWeatherListAPI(@Query("q") String destination, @Query("days") int days);

    @GET("v1/forecast.json?key=2fc725cddfc84683bc9160939212101")
    Call<GetForecast> getForecastAPI(@Query("q") String destination, @Query("days") int days);

    @GET("v1/search.json?key=2fc725cddfc84683bc9160939212101")
    Call<List<searchLocation>> getSearchLocationAPI(@Query("q") String cityName);


}
