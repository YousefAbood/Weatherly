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
    private SectionsPagerAdapter mSectionsPagerAdapter;

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

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragment()).commit();
//        } else {
//            selectedItemId = savedInstanceState.getInt("selectedItemId", R.id.nav_home);
//        }


        // BottomNavigation

//        bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);


        // TabLayout & ViewPager

        tabLayoutMainActivity = findViewById(R.id.tabLayoutMainActivity);
        viewPagerMainActivity = findViewById(R.id.viewPagerMainActivity);

//        tabLayoutMainActivity.addTab(tabLayoutMainActivity.newTab().setText("Home"));
//        tabLayoutMainActivity.addTab(tabLayoutMainActivity.newTab().setText("Cities"));
//        tabLayoutMainActivity.addTab(tabLayoutMainActivity.newTab().setText("Search Cities"));
//        tabLayoutMainActivity.setTabGravity(TabLayout.GRAVITY_FILL);
//        adapter = new myAdapter(this,getSupportFragmentManager(), tabLayoutMainActivity.getTabCount());
//
//        viewPagerMainActivity.setAdapter(adapter);
//
//        viewPagerMainActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMainActivity));
//
//        tabLayoutMainActivity.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPagerMainActivity.setCurrentItem(tab.getPosition());
//                Log.d(TAG, "onTab: " + tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//                Log.d(TAG, "onTab: " + tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Log.d(TAG, "onTab: " + tab.getPosition());
//
//
//            }
//        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        viewPagerMainActivity.setAdapter(mSectionsPagerAdapter);
        tabLayoutMainActivity.setupWithViewPager(viewPagerMainActivity);




    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new CitiesFragment();
                    break;
                case 2:
                    fragment = new SearchCitiesFragment();
                    break;
            }

            return fragment;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Cities";
                case 2:
                    return "Search Cities";
            }

            return null;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(mSectionsPagerAdapter == null)) {
            mSectionsPagerAdapter.notifyDataSetChanged();
        }

    }

    // Bottom Navigation
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    selectedItemId = item.getItemId();
//
//                    Fragment selectedFragment = null;
//                    switch (item.getItemId()) {
//                        case R.id.nav_home:
//                            selectedFragment = mHomeFragment;
//                            break;
//                        case R.id.nav_cities:
//                            selectedFragment = mCitiesFragment;
//                            break;
//                        case R.id.nav_search:
//                            selectedFragment = mSearchFragment;
//                            break;
//                    }
//
//                    return loadFragment(selectedFragment);
//                }
//            };



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




