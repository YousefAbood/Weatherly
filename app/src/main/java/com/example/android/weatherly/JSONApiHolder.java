package com.example.android.weatherly;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONApiHolder {

    @GET("v1/current.json?key=2fc725cddfc84683bc9160939212101&q=Almere")
    Call<CurrentWeatherList> getPosts();


}
