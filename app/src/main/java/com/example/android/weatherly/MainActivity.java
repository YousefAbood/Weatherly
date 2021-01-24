package com.example.android.weatherly;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RetroFit";
    private JSONApiHolder JSONApiHolder;

    // Longitude & Latitude
    String longitudeS, latitudeS;

    // Current Weather Condition DataAdapter Parameters
    public ArrayList<String> mCurrentTemperature = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherCondition = new ArrayList<>();
    public ArrayList<String> mDayOrNight = new ArrayList<>();
    public ArrayList<String> mLocationCurrentWeatherConditions = new ArrayList<>();
    public ArrayList<String> mRealFeelTemp = new ArrayList<>();
    public ArrayList<String> mWindSpeed = new ArrayList<>();
    public ArrayList<String> mHumidity = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionIcon = new ArrayList<>();

    // Forecast DataAdapter Parameters
    public ArrayList<String> mDayOfTheWeek = new ArrayList<>();
    public ArrayList<String> mMaxTemp = new ArrayList<>();
    public ArrayList<String> mMinTemp = new ArrayList<>();
    public ArrayList<String> mForecastIcon = new ArrayList<>();
    public int days = 5;

    // GPS Location
    private GPSLocation gpsLocation;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLocation();
        Log.d(TAG, "onCreate: " + latitudeS + longitudeS);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(JSONApiHolder.class);
        forecastConditions();
        currentWeatherCondition();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initRecyclerViewCurrentConditions() {
        RecyclerView recyclerView = findViewById(R.id.currentConditionsRecyclerView);
        currentWeatherConditionsDataAdapter adapter = new currentWeatherConditionsDataAdapter(this, mCurrentTemperature, mCurrentWeatherCondition, mDayOrNight, mLocationCurrentWeatherConditions, mRealFeelTemp, mWindSpeed, mHumidity, mCurrentWeatherConditionIcon);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initRecyclerViewForecast() {
        RecyclerView recyclerView = findViewById(R.id.forecastRecyclerView);
        forecastDataAdapter adapter = new forecastDataAdapter(this, mDayOfTheWeek, mMinTemp, mMaxTemp, mForecastIcon);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void currentWeatherCondition() {

        Call<CurrentWeatherList> call = JSONApiHolder.getCurrentWeatherList(getLocation(), 1);

        call.enqueue(new Callback<CurrentWeatherList>() {
            public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "CurrentWeatherConditionCode: " + response.code());
                    return;
                }

                CurrentWeatherList currentWeatherLists = response.body();


                mCurrentTemperature.add(currentWeatherLists.getCurrent().getTemperatureCelcius());
                mCurrentWeatherCondition.add(currentWeatherLists.getCurrent().getCondition().getWeatherCondition());
                mDayOrNight.add(currentWeatherLists.getCurrent().getDayOrNight());
                mLocationCurrentWeatherConditions.add(currentWeatherLists.getLocationAPI().getCityAndCountryName());
                mRealFeelTemp.add(currentWeatherLists.getCurrent().getFeelsLikeCelcius());
                mWindSpeed.add(currentWeatherLists.getCurrent().getWindSpeedKPH());
                mHumidity.add(currentWeatherLists.getCurrent().getHumidity());
                mCurrentWeatherConditionIcon.add(currentWeatherLists.getCurrent().getCondition().getImageIconURL());


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

    private void forecastConditions() {
        Call<GetForecast> call = JSONApiHolder.getForecast(getLocation(), 3);

        call.enqueue(new Callback<GetForecast>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<GetForecast> call, Response<GetForecast> response) {

                GetForecast getForecastParent = response.body();

                List<forecastday> forecastdaysList = getForecastParent.getForecast().getForecastdayList();


                mDayOfTheWeek.clear();
                mMinTemp.clear();
                mMaxTemp.clear();
                mForecastIcon.clear();


//                Log.d(TAG, "onResponse: " + getDayOfWeek(year, month, day));

                for (forecastday post : forecastdaysList) {

//                    for(int i = 0; i < days; i++) {}


                    Log.d(TAG, "onResponse: " + post.getDay().getMinTempC());
                    Log.d(TAG, "onResponse: " + post.getDay().getMaxTempC());

                    mDayOfTheWeek.add(getDayOfWeek(post.getDate()));
                    mMinTemp.add(post.getDay().getMinTempC());
                    mMaxTemp.add(post.getDay().getMaxTempC());
                    mForecastIcon.add(post.getDay().getCondition().getImageIconURL());
                    initRecyclerViewForecast();
                }

            }

            @Override
            public void onFailure(Call<GetForecast> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                } else {
                }
                Log.d(TAG, "Code: " + t.getMessage());

            }

        });
    }


    public String getDayOfWeek(String date) {

        String yearS = date.substring(0, 4);
        String monthS = date.substring(5, 7);
        String dayS = date.substring(8, 10);


        String[] weekDays = new String[]{
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        int day = Integer.parseInt(dayS);
        int month = Integer.parseInt(monthS);
        int year = Integer.parseInt(yearS);


        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        int dayOfWeekInt = c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekString = weekDays[dayOfWeekInt - 1];

        return dayOfWeekString;
    }


    // Location

    public String getLocation() {
        gpsLocation = new GPSLocation(MainActivity.this);
        if (gpsLocation.canGetLocation()) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String latitudeS = String.valueOf(latitude);
            String longitudeS = String.valueOf(longitude);
            String latlong = latitudeS + "," + longitudeS;
            return latlong;
        } else {
            gpsLocation.showSettingsAlert();
            return "nothing";
        }
    }
}



