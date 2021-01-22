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
    public ArrayList<String> mCurrentTemperature = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherCondition = new ArrayList<>();
    public ArrayList<String> mDayOrNight = new ArrayList<>();
    public ArrayList<String> mLocation = new ArrayList<>();
    public ArrayList<String> mRealFeelTemp = new ArrayList<>();
    public ArrayList<String> mWindSpeed = new ArrayList<>();
    public ArrayList<String> mHumidity = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionIcon = new ArrayList<>();
    public RecyclerView recyclerView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(JSONApiHolder.class);

        initRecyclerViewCurrentConditions();

        currentWeatherCondition();



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initRecyclerViewCurrentConditions() {
        RecyclerView recyclerView = findViewById(R.id.mainActivityRecyclerView);
        currentWeatherConditionsDataAdapter adapter = new currentWeatherConditionsDataAdapter(this, mCurrentTemperature, mCurrentWeatherCondition, mDayOrNight, mLocation, mRealFeelTemp, mWindSpeed, mHumidity, mCurrentWeatherConditionIcon);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void currentWeatherCondition() {
        Call<CurrentWeatherList> call = JSONApiHolder.getPosts();

        call.enqueue(new Callback<CurrentWeatherList>() {
            public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "CurrentWeatherConditionCode: " + response.code());
                    return;
                }

                CurrentWeatherList currentWeatherLists = response.body();

                mCurrentTemperature.add(currentWeatherLists.getCurrent().getTemperatureCelcius());
                Log.d(TAG, "onResponse: " +  currentWeatherLists.getCurrent().getTemperatureCelcius());

                initRecyclerViewCurrentConditions();

            }

            public void onFailure(Call<CurrentWeatherList> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                } else {
                }
                Log.d(TAG, "Code: " + t.getMessage());

            }
        });
    }

}

