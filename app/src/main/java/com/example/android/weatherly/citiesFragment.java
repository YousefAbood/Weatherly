package com.example.android.weatherly;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class citiesFragment extends Fragment {

    private static final String TAG = "citiesFragment";
    String cityName;
    ArrayList<String> mCityNames = new ArrayList<>();
    View view;
    RecyclerView recyclerView;
    // This fragment will contain the Cities added after clicking on Add Cities, Going to searchLocationFragment, Clicking on the city, Prompting whether you want add the city or not
    // If Yes then you add it to an adapter thats attached to a RecyclerView in citiesFragment,
    // TO-DO List
    // RetroFit Call for Search
    // RecyclerView & Adapter for Added Cities
    // RecyclerView & Adapter for SearchView




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        Bundle myBundle = new Bundle();

        Intent intent = new Intent();
        String cityNameFragment = myBundle.containsKey("cityName") ? myBundle.getString("cityName") : "default";


        Log.d(TAG, "onCreateView: " + cityNameFragment);


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cities, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.citiesRecyclerView);


        initRecyclerViewCity();


        // Toolbar Part
        Toolbar toolbarCities;
//        toolbarCities = (Toolbar) rootView.findViewById(R.id.toolbarCities);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(rootView.findViewById(R.id.toolbarCities));



        return rootView;
    }



    private void initRecyclerViewCity() {
        View view;
        mCityNames.add("1");
        searchCitiesAdapter adapter = new searchCitiesAdapter(getActivity(), mCityNames);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }





}