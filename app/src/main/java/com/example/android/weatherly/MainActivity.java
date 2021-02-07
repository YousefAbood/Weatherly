package com.example.android.weatherly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
//    private JSONApiHolder JSONApiHolder;
//
//    // Longitude & Latitude
//    String longitudeS, latitudeS;
//
//    // Current Weather Condition DataAdapter Parameters
//    public ArrayList<String> mDayOrNight = new ArrayList<>();
//    public ArrayList<String> mLocationCurrentWeatherConditions = new ArrayList<>();
//    public ArrayList<String> mCurrentTemperature = new ArrayList<>();
//    public ArrayList<String> mMaxTemperature = new ArrayList<>();
//    public ArrayList<String> mMinTemperature = new ArrayList<>();
//    public ArrayList<String> mCurrentWeatherCondition = new ArrayList<>();
//    public ArrayList<String> mRealFeelTemp = new ArrayList<>();
//    public ArrayList<String> mCurrentWeatherConditionIcon = new ArrayList<>();
//    public ArrayList<String> mSunriseTime = new ArrayList<>();
//    public ArrayList<String> mSunsetTime = new ArrayList<>();
//    public ArrayList<String> mPrecipitation = new ArrayList<>();
//    public ArrayList<String> mHumidity = new ArrayList<>();
//    public ArrayList<String> mWindSpeed = new ArrayList<>();
//    public ArrayList<String> mPressure = new ArrayList<>();
//
//
//
//    // Forecast DataAdapter Parameters
//    public ArrayList<String> mDayOfTheWeek = new ArrayList<>();
//    public ArrayList<String> mMaxTemp = new ArrayList<>();
//    public ArrayList<String> mMinTemp = new ArrayList<>();
//    public ArrayList<String> mForecastIcon = new ArrayList<>();
//    public int days = 5;
//
//    // GPS Location
//    private GPSLocation gpsLocation;
//
//    // Swipe To Refresh
//    SwipeRefreshLayout mSwipeRefreshLayout;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCities);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // -----

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new homeFragment()).commit();
        }

        // Receive info



    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new homeFragment();
                            break;
                        case R.id.nav_cities:
                            selectedFragment = new citiesFragment();
//                            Bundle bundle = new Bundle();
//
////                            bundle.putString("cityName", getIntent().getStringExtra("cityName"));
//                            String cityNameMA = getIntent().getStringExtra("cityName");
//                            selectedFragment.setArguments(bundle);
//
//                            Log.d(TAG, "bundle thing " + cityNameMA);
                            break;
                        case R.id.nav_search:
                            selectedFragment = new searchCitiesFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    // -------------------------------

//        getLocation();
//        Log.d(TAG, "onCreate: " + latitudeS + longitudeS);
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                currentWeatherCondition();
//                forecastConditions();
//
//            }
//        });
//        // Configure the refreshing colors
//        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//
//        try {
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.weatherapi.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//        JSONApiHolder = retrofit.create(JSONApiHolder.class);
//        currentWeatherCondition();
//        forecastConditions();


//    private void initRecyclerViewCurrentConditions() {
//        RecyclerView recyclerView = findViewById(R.id.currentConditionsRecyclerView);
//        currentWeatherConditionsDataAdapter adapter = new currentWeatherConditionsDataAdapter(this, mDayOrNight, mLocationCurrentWeatherConditions, mCurrentTemperature, mMaxTemperature, mMinTemperature, mCurrentWeatherCondition, mCurrentWeatherConditionIcon, mRealFeelTemp, mSunriseTime, mSunsetTime, mPrecipitation, mHumidity, mWindSpeed, mPressure);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//
//    private void initRecyclerViewForecast() {
//        RecyclerView recyclerView = findViewById(R.id.forecastRecyclerView);
//        forecastDataAdapter adapter = new forecastDataAdapter(this, mDayOfTheWeek, mMinTemp, mMaxTemp, mForecastIcon);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//
//    private void currentWeatherCondition() {
//
//        Call<CurrentWeatherList> call = JSONApiHolder.getCurrentWeatherList(getLocation(), 1);
//
//        call.enqueue(new Callback<CurrentWeatherList>() {
//            public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
//                if (!response.isSuccessful()) {
//                    Log.d(TAG, "CurrentWeatherConditionCode: " + response.code());
//                    return;
//                }
//
//                CurrentWeatherList currentWeatherLists = response.body();
//
//                mDayOrNight.clear();
//                mLocationCurrentWeatherConditions.clear();
//                mCurrentTemperature.clear();
//                mMaxTemperature.clear();
//                mMinTemperature.clear();
//                mCurrentWeatherCondition.clear();
//                mRealFeelTemp.clear();
//                mCurrentWeatherConditionIcon.clear();
//                mSunriseTime.clear();
//                mSunsetTime.clear();
//                mPrecipitation.clear();
//                mHumidity.clear();
//                mWindSpeed.clear();
//                mPressure.clear();
//
//
//
//
//                mDayOrNight.add(currentWeatherLists.getCurrent().getDayOrNight());
//                mLocationCurrentWeatherConditions.add(currentWeatherLists.getLocationAPI().getCityAndCountryName());
//
//                // -----
//
//                mCurrentTemperature.add(currentWeatherLists.getCurrent().getTemperatureCelcius());
//                mMaxTemperature.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());
//
//                Log.d(TAG, "onResponse: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());
//                Log.d(TAG, "onResponse: " + mMaxTemperature.size());
//
//                Log.d(TAG, "onResponse: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());
//
//                mMinTemperature.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());
//                mCurrentWeatherCondition.add(currentWeatherLists.getCurrent().getCondition().getWeatherCondition());
//                mRealFeelTemp.add(currentWeatherLists.getCurrent().getFeelsLikeCelcius());
//                mCurrentWeatherConditionIcon.add(currentWeatherLists.getCurrent().getCondition().getImageIconURL());
//
//                // -----
//                mSunriseTime.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getAstro().getSunrise());
//                mSunsetTime.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getAstro().getSunset());
//                mPrecipitation.add(currentWeatherLists.getCurrent().getPrecipMM());
//                mHumidity.add(currentWeatherLists.getCurrent().getHumidity());
//                mWindSpeed.add(currentWeatherLists.getCurrent().getWindSpeedKPH());
//                mPressure.add(currentWeatherLists.getCurrent().getPressureMB());
//
//                initRecyclerViewCurrentConditions();
//                mSwipeRefreshLayout.setRefreshing(false);
//
//
//            }
//
//            public void onFailure(Call<CurrentWeatherList> call, Throwable t) {
//                if (t instanceof IOException) {
//                    Log.e(TAG, "onFailure: ");
//
//                } else {
//                }
//                mSwipeRefreshLayout.setRefreshing(false);
//
//                Log.d(TAG, "Code: " + t.getMessage());
//
//            }
//        });
//    }
//
//    private void forecastConditions() {
//        Call<GetForecast> call = JSONApiHolder.getForecast(getLocation(), 3);
//
//        call.enqueue(new Callback<GetForecast>() {
//            @Override
//            public void onResponse(Call<GetForecast> call, Response<GetForecast> response) {
//                if (!response.isSuccessful()) {
//                    Log.d(TAG, "ForecastCode: " + response.code());
//                    return;
//                }
//
//                GetForecast getForecastParent = response.body();
//
//                List<forecastday> forecastdaysList = getForecastParent.getForecast().getForecastdayList();
//
//
//                mDayOfTheWeek.clear();
//                mMinTemp.clear();
//                mMaxTemp.clear();
//                mForecastIcon.clear();
//
//
////                Log.d(TAG, "onResponse: " + getDayOfWeek(year, month, day));
//
//                for (forecastday post : forecastdaysList) {
//
////                    for(int i = 0; i < days; i++) {}
//
//
////                    Log.d(TAG, "onResponse: " + post.getDay().getMinTempC());
////                    Log.d(TAG, "onResponse: " + post.getDay().getMaxTempC());
//
//                    mDayOfTheWeek.add(getDayOfWeek(post.getDate()));
//                    mMinTemp.add(post.getDay().getMinTempC());
//                    mMaxTemp.add(post.getDay().getMaxTempC());
//                    mForecastIcon.add(post.getDay().getCondition().getImageIconURL());
//                    initRecyclerViewForecast();
//                    mSwipeRefreshLayout.setRefreshing(false);
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetForecast> call, Throwable t) {
//                if (t instanceof IOException) {
//                    Log.e(TAG, "onFailure: ");
//                } else {
//                }
//                Log.d(TAG, "Code: " + t.getMessage());
//                mSwipeRefreshLayout.setRefreshing(false);
//
//            }
//
//        });
//    }
//
//
//    public String getDayOfWeek(String date) {
//        String yearS = date.substring(0, 4);
//        String monthS = date.substring(5, 7);
//        String dayS = date.substring(8, 10);
//
//        String[] weekDays = new String[]{
//                "Sunday",
//                "Monday",
//                "Tuesday",
//                "Wednesday",
//                "Thursday",
//                "Friday",
//                "Saturday"
//        };
//
//        int day = Integer.parseInt(dayS);
//        int month = Integer.parseInt(monthS);
//        int year = Integer.parseInt(yearS);
//
//        Calendar c = Calendar.getInstance();
//        c.set(year, month - 1, day);
//        int dayOfWeekInt = c.get(Calendar.DAY_OF_WEEK);
//        String dayOfWeekString = weekDays[dayOfWeekInt - 1];
//
//        return dayOfWeekString;
//    }
//
//
//    // Location
//    public String getLocation() {
//        gpsLocation = new GPSLocation(MainActivity.this);
//        if (gpsLocation.canGetLocation()) {
//            double latitude = gpsLocation.getLatitude();
//            double longitude = gpsLocation.getLongitude();
//            String latitudeS = String.valueOf(latitude);
//            String longitudeS = String.valueOf(longitude);
//            String latlong = latitudeS + "," + longitudeS;
//            return latlong;
//        } else {
//            gpsLocation.showSettingsAlert();
//            return "nothing";
//        }
//    }
}



