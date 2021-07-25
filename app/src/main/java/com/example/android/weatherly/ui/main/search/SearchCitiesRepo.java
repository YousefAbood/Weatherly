package com.example.android.weatherly.ui.main.search;

import android.util.Log;

import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.searchLocation;

import java.io.IOException;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchCitiesRepo {

    private static final String TAG = "SearchCitiesRepo";

    private JSONApiHolder jsonApiHolder;

    private final BehaviorSubject<List<searchLocation>> mSearchLocationBehaviourSubject = BehaviorSubject.create();


    public SearchCitiesRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApiHolder = retrofit.create(JSONApiHolder.class);

    }

    public void getSearchLocationResults(String CityName) {

        Call<List<searchLocation>> call = jsonApiHolder.getSearchLocation(CityName);

        call.enqueue(new Callback<List<searchLocation>>() {
            @Override
            public void onResponse(Call<List<searchLocation>> call, Response<List<searchLocation>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "response code " + response.code());
                    return;
                }

                List<searchLocation> searchLocations = response.body();

                mSearchLocationBehaviourSubject.onNext(searchLocations);
            }

            @Override
            public void onFailure(Call<List<searchLocation>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: " + t);
                }

            }
        });
    }


    public BehaviorSubject<List<searchLocation>> getmSearchLocationBehaviourSubject() {
        return mSearchLocationBehaviourSubject;
    }
}
