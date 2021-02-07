package com.example.android.weatherly.data.api;

import com.example.android.weatherly.data.model.CurrentWeatherList;
import com.example.android.weatherly.data.model.GetForecast;
import com.example.android.weatherly.data.model.searchLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONApiHolder {


    @GET("v1/forecast.json?key=2fc725cddfc84683bc9160939212101")
    Call<CurrentWeatherList> getCurrentWeatherList(@Query("q") String longLat, @Query("days") int days);

    @GET("v1/forecast.json?key=2fc725cddfc84683bc9160939212101")
    Call<GetForecast> getForecast(@Query("q") String longLat, @Query("days") int days);

    @GET("v1/search.json?key=2fc725cddfc84683bc9160939212101")
    Call<List<searchLocation>> getSearchLocation(@Query("q") String cityName);


}
