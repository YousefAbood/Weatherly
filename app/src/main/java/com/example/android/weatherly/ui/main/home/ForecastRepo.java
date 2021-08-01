package com.example.android.weatherly.ui.main.home;

import android.util.Log;

import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.Forecast.GetForecast;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {

    private static final String TAG = "ForecastRepo";

    private JSONApiHolder jsonApiHolder;

    private final BehaviorSubject<GetForecast> mForecastBehaviourSubject = BehaviorSubject.create();

    public ForecastRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApiHolder = retrofit.create(JSONApiHolder.class);

    }

    public void getForecast(String destination, int days) {
        Log.d(TAG, "initRecyclerViewCurrentConditionsForecast: ");


        Call<GetForecast> call = jsonApiHolder.getForecastAPI(destination, days);

        call.enqueue(new Callback<GetForecast>() {
            @Override
            public void onResponse(Call<GetForecast> call, Response<GetForecast> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "response code " + response.code());
                    return;
                }




                GetForecast getForecast = response.body();

                Log.d(TAG, "initRecyclerViewCurrentConditionsForecastTTT: " );

                mForecastBehaviourSubject.onNext(getForecast);

            }

            @Override
            public void onFailure(Call<GetForecast> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: " + t);
                }

                Log.d(TAG, "onFailure: ");
            }
        });

    }


    public BehaviorSubject<GetForecast> getmForecastBehaviourSubjectRepo() {
        return mForecastBehaviourSubject;
    }


}
