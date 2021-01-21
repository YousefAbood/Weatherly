package com.example.android.weatherly;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import android.view.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "RetroFit";
    private JSONApiHolder JSONApiHolder;
    public ArrayList<String> mCurrentWeatherCondition = new ArrayList<>();
    public ArrayList<String> mTemperature = new ArrayList<>();
    public ArrayList<String> mCountry = new ArrayList<>();
    public ArrayList<String> mCity = new ArrayList<>();
    public ArrayList<String> mWeatherIcon = new ArrayList<>();
    public ArrayList<String> mRealFeelTemperature = new ArrayList<>();
    public ArrayList<String> mWindSpeed = new ArrayList<>();
    public ArrayList<String> mHumidity = new ArrayList<>();
    public int locationKey = 247498;
    public RecyclerView recyclerView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(JSONApiHolder.class);

        currentWeatherCondition();
        currentLocation();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initRecyclerViewCurrentConditions() {
        RecyclerView recyclerView = findViewById(R.id.mainActivityRecyclerView);
        currentWeatherConditionsDataAdapter adapter = new currentWeatherConditionsDataAdapter(this, mCurrentWeatherCondition, mCountry, mCity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void currentWeatherCondition() {
        Call<List<CurrentWeatherList>> call = JSONApiHolder.getPosts(locationKey);

        call.enqueue(new Callback<List<CurrentWeatherList>>() {
            public void onResponse(Call<List<CurrentWeatherList>> call, Response<List<CurrentWeatherList>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "CurrentWeatherConditionCode: " + response.code());
                    return;
                }

                List<CurrentWeatherList> currentWeatherLists = response.body();

                for (CurrentWeatherList post : currentWeatherLists) {
                    mCurrentWeatherCondition.add(post.getWind().getSpeed().getMetric().getValue());
                    mTemperature.add(post.getTemperature().getMetric().getValue());


                }

                initRecyclerViewCurrentConditions();



            }

            public void onFailure(Call<List<CurrentWeatherList>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                } else {
                }
                Log.d(TAG, "Code: " + t.getMessage());

            }
        });
    }

    private void currentLocation() {
        Call<Location> call = JSONApiHolder.getLocation(locationKey);

        call.enqueue(new Callback<Location>() {
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "LocationCode: " + response.code());
                    return;
                }

                Location locationsRes = response.body();
                mCountry.add(locationsRes.getLocationCountry().getCountryName());
                mCity.add(locationsRes.getCityName());

                initRecyclerViewCurrentConditions();
            }

            public void onFailure(Call<Location> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                } else {

                }
                Log.d(TAG, "LocationCode: " + t.getMessage());
            }
        });

    }

}

