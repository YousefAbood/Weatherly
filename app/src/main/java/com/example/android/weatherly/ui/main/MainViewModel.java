package com.example.android.weatherly.ui.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final SavedStateHandle handle;
    private final MutableLiveData<List<String>> cityNames;

    public MainViewModel(SavedStateHandle handle) {
        this.handle = handle;
        this.cityNames = handle.getLiveData("cityNames", Collections.emptyList());
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
    }
}
