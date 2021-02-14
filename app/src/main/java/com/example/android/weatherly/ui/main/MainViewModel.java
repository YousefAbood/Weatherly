package com.example.android.weatherly.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.weatherly.R;
import com.example.android.weatherly.ui.main.cities.CitiesFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<List<String>> cityNames = new MutableLiveData<>(Collections.emptyList());

    private static final String TAG = "MainViewModel";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    @SuppressWarnings("deprecation")
    public MainViewModel(Application application) {
        super(application);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);

        String jsonNames = sharedPreferences.getString("cityNames", "[]");

        cityNames.setValue(gson.fromJson(jsonNames, new TypeToken<ArrayList<String>>() {}.getType()));

    }

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
}
