package com.example.android.weatherly.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.weatherly.data.model.CurrentWeatherList.CurrentWeatherList;
import com.example.android.weatherly.data.model.GetForecast.GetForecast;
import com.example.android.weatherly.data.model.searchLocation;
import com.example.android.weatherly.ui.main.home.CurrentWeatherRepo;
import com.example.android.weatherly.ui.main.home.ForecastRepo;
import com.example.android.weatherly.ui.main.search.SearchCitiesRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<List<String>> cityNames = new MutableLiveData<>(Collections.emptyList());

    private static final String TAG = "MainViewModel";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();


    // BehaviourSubject
    private BehaviorSubject<CurrentWeatherList> mCurrentWeatherList = BehaviorSubject.create();
    private BehaviorSubject<GetForecast> mForecastBehaviourSubject = BehaviorSubject.create();
    private BehaviorSubject<List<searchLocation>> mSearchLocationBehaviourSubject = BehaviorSubject.create();


    public MainViewModel(Application application) {
        super(application);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);

        String jsonNames = sharedPreferences.getString("cityNames", "[]");

        cityNames.setValue(gson.fromJson(jsonNames, new TypeToken<ArrayList<String>>() {}.getType()));

    }


    // City Name stuff
    public LiveData<List<String>> getCityNames() {
        return cityNames;
    }

    public void addCityName(String cityName) {
        List<String> newCityNames = new ArrayList<>(this.cityNames.getValue());

        if (!newCityNames.contains(cityName)) {
            newCityNames.add(cityName);
        }




        cityNames.setValue(Collections.unmodifiableList(newCityNames));

        sharedPreferences.edit().putString("cityNames", gson.toJson(newCityNames)).apply();
    }


    public void removeCityName(String cityName) {
        List<String> newCityNamesRemoved = new ArrayList<>(this.cityNames.getValue());

        if (newCityNamesRemoved.contains(cityName)) {

            Log.d(TAG, "removeCityName: Success");
            int position = newCityNamesRemoved.indexOf(cityName);
            newCityNamesRemoved.remove(position);
        }

        cityNames.setValue(Collections.unmodifiableList(newCityNamesRemoved));

        sharedPreferences.edit().putString("cityNames", gson.toJson(newCityNamesRemoved)).apply();
    }



    // Current Weather & Forecast

    public void CurrentForecast(String destination, int days) {
        CurrentWeatherRepo currentWeatherRepo = new CurrentWeatherRepo();
        currentWeatherRepo.getCurrentWeather(destination, days);
        mCurrentWeatherList = currentWeatherRepo.getmCurrentWeatherList();
    }

    public BehaviorSubject<CurrentWeatherList> getmCurrentWeatherList() {
        return mCurrentWeatherList;
    }

    public void Forecast(String destination, int days) {
        ForecastRepo forecastRepo = new ForecastRepo();
        forecastRepo.getForecast(destination, days);
        mForecastBehaviourSubject = forecastRepo.getmForecastBehaviourSubject();
    }

    public BehaviorSubject<GetForecast> getmForecastBehaviourSubject() {
        return mForecastBehaviourSubject;
    }

    // Search Location


    public void SearchLocation(String destination) {
        SearchCitiesRepo searchCitiesRepo = new SearchCitiesRepo();
        searchCitiesRepo.getSearchLocationResults(destination);
        mSearchLocationBehaviourSubject = searchCitiesRepo.getmSearchLocationBehaviourSubject();
    }

    public BehaviorSubject<List<searchLocation>> getmSearchLocationBehaviourSubject() {
        return mSearchLocationBehaviourSubject;
    }
}
