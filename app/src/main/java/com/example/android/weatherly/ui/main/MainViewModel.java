package com.example.android.weatherly.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<List<String>> cityNames = new MutableLiveData<>(Collections.emptyList());

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
}
