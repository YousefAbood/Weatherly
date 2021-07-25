package com.example.android.weatherly.ui.main.home;

import android.util.Log;

import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.CurrentWeatherList.CurrentWeatherList;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrentWeatherRepo {

    private static final String TAG = "CurrentWeatherForecastR";

    private JSONApiHolder jsonApiHolder;

    private final BehaviorSubject<CurrentWeatherList> mCurrentWeatherList = BehaviorSubject.create();

    public CurrentWeatherRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApiHolder = retrofit.create(JSONApiHolder.class);
    }


    public void getCurrentWeather(String LatLong, int days) {

        Call<CurrentWeatherList> call = jsonApiHolder.getCurrentWeatherList(LatLong, days);

        call.enqueue(new Callback<CurrentWeatherList>() {
            @Override
            public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "Response Code: " + response.code());
                    return;
                }

                CurrentWeatherList currentWeatherList = response.body();

                mCurrentWeatherList.onNext(currentWeatherList);
            }

            @Override
            public void onFailure(Call<CurrentWeatherList> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: " + t);
                }

            }
        });
    }


    public BehaviorSubject<CurrentWeatherList> getmCurrentWeatherList() {
        return mCurrentWeatherList;
    }
}
