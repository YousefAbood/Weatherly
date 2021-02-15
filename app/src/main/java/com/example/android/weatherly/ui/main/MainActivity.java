package com.example.android.weatherly.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
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
    TabLayout tabLayoutMainActivity;
    ViewPager viewPagerMainActivity;

    private int selectedItemId = R.id.nav_home;

    private BottomNavigationView bottomNav;

    private Fragment mHomeFragment = new HomeFragment();
    private Fragment mCitiesFragment = new CitiesFragment();
    private Fragment mSearchFragment = new SearchCitiesFragment();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCities);
        setSupportActionBar(toolbar);


//

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        } else {
            selectedItemId = savedInstanceState.getInt("selectedItemId", R.id.nav_home);
        }


        // BottomNavigation

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }



//     Bottom Navigation
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedItemId = item.getItemId();

                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = mHomeFragment;
                            break;
                        case R.id.nav_cities:
                            selectedFragment = mCitiesFragment;
                            break;
                        case R.id.nav_search:
                            selectedFragment = mSearchFragment;
                            break;
                    }

                    return loadFragment(selectedFragment);
                }
            };



    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}


    // -------------------------------

