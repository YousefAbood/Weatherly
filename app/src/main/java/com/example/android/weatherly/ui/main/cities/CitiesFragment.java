package com.example.android.weatherly.ui.main.cities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.android.weatherly.R;
import com.example.android.weatherly.ui.main.MainViewModel;
import com.example.android.weatherly.ui.main.search.SearchCitiesAdapter;


public class CitiesFragment
        extends Fragment {
    public CitiesFragment() {
        super(R.layout.fragment_cities);
    }

    // This fragment will contain the Cities added after clicking on Add Cities, Going to searchLocationFragment, Clicking on the city, Prompting whether you want add the city or not
    // If Yes then you add it to an adapter thats attached to a RecyclerView in citiesFragment,
    // TO-DO List
    // RetroFit Call for Search
    // RecyclerView & Adapter for Added Cities
    // RecyclerView & Adapter for SearchView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.citiesRecyclerView);

        CitiesAdapter adapter = new CitiesAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(view.findViewById(R.id.toolbarCities));

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.getCityNames().observe(getViewLifecycleOwner(), (data) -> {
            //noinspection Convert2MethodRef
            adapter.updateData(data);
        });
    }
}