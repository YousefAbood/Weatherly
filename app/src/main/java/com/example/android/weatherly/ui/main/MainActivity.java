package com.example.android.weatherly.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;

import com.example.android.weatherly.R;
import com.example.android.weatherly.ui.main.search.SearchCitiesFragment;
import com.example.android.weatherly.ui.main.cities.CitiesFragment;
import com.example.android.weatherly.ui.main.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView mBottomNavigation =  findViewById(R.id.bottom_navigation);

        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        final NavController navController;

        if(navHostFragment != null) {

            navController = navHostFragment.getNavController();

            NavigationUI.setupWithNavController(mBottomNavigation, navController);
        }



    }




}


    // -------------------------------

