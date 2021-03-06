package com.example.android.weatherly.ui.main.home;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.weatherly.R;
import com.example.android.weatherly.data.model.CurrentWeatherList;
import com.example.android.weatherly.app.location.GPSLocation;
import com.example.android.weatherly.data.model.GetForecast;
import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.forecastday;
import com.example.android.weatherly.ui.main.cities.CitiesFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment
        extends Fragment {

    Context context;

    // Fragment Settings

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocation();
        Log.d(TAG, "onCreate: " + latitudeS + longitudeS);


        try {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(com.example.android.weatherly.data.api.JSONApiHolder.class);
        currentWeatherCondition();
        forecastConditions();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        // TextView
        dayDate = (TextView) rootView.findViewById(R.id.day_date);

        // Toolbar Part
//        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.homeToolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Swipe to Refresh Layout
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentWeatherCondition();
                forecastConditions();

            }
        });
        // Configure the refreshing colors
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        return rootView;
    }


    // Main Activity

    private static final String TAG = "Home";
    private JSONApiHolder JSONApiHolder;

    // Longitude & Latitude
    String longitudeS, latitudeS;

    // Current Weather Condition DataAdapter Parameters
    public ArrayList<String> mDayOrNight = new ArrayList<>();
    public ArrayList<String> mLocationCurrentWeatherConditions = new ArrayList<>();
    public ArrayList<String> mCurrentTemperature = new ArrayList<>();
    public ArrayList<String> mMaxTemperature = new ArrayList<>();
    public ArrayList<String> mMinTemperature = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherCondition = new ArrayList<>();
    public ArrayList<String> mRealFeelTemp = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionIcon = new ArrayList<>();
    public ArrayList<String> mSunriseTime = new ArrayList<>();
    public ArrayList<String> mSunsetTime = new ArrayList<>();
    public ArrayList<String> mPrecipitation = new ArrayList<>();
    public ArrayList<String> mHumidity = new ArrayList<>();
    public ArrayList<String> mWindSpeed = new ArrayList<>();
    public ArrayList<String> mPressure = new ArrayList<>();


    // Forecast DataAdapter Parameters
    public ArrayList<String> mDayOfTheWeek = new ArrayList<>();
    public ArrayList<String> mMaxTemp = new ArrayList<>();
    public ArrayList<String> mMinTemp = new ArrayList<>();
    public ArrayList<String> mForecastIcon = new ArrayList<>();

    // GPS Location
    private GPSLocation gpsLocation;

    // Swipe To Refresh
    SwipeRefreshLayout mSwipeRefreshLayout;

    // TextView
    TextView dayDate;

    private void initRecyclerViewCurrentConditions() {
        RecyclerView recyclerView = getView().findViewById(R.id.currentConditionsRecyclerView);
        CurrentWeatherConditionsDataAdapter adapter = new CurrentWeatherConditionsDataAdapter(getActivity(), mDayOrNight, mLocationCurrentWeatherConditions, mCurrentTemperature, mMaxTemperature, mMinTemperature, mCurrentWeatherCondition, mCurrentWeatherConditionIcon, mRealFeelTemp, mSunriseTime, mSunsetTime, mPrecipitation, mHumidity, mWindSpeed, mPressure);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void initRecyclerViewForecast() {
        RecyclerView recyclerView = getView().findViewById(R.id.forecastRecyclerView);
        ForecastDataAdapter adapter = new ForecastDataAdapter(getActivity(), mDayOfTheWeek, mMinTemp, mMaxTemp, mForecastIcon);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void currentWeatherCondition() {

        Call<CurrentWeatherList> call = JSONApiHolder.getCurrentWeatherList(getLocation(), 1);

        call.enqueue(new Callback<CurrentWeatherList>() {
            public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "CurrentWeatherConditionCode: " + response.code());
                    return;
                }

                CurrentWeatherList currentWeatherLists = response.body();

                mDayOrNight.clear();
                mLocationCurrentWeatherConditions.clear();
                mCurrentTemperature.clear();
                mMaxTemperature.clear();
                mMinTemperature.clear();
                mCurrentWeatherCondition.clear();
                mRealFeelTemp.clear();
                mCurrentWeatherConditionIcon.clear();
                mSunriseTime.clear();
                mSunsetTime.clear();
                mPrecipitation.clear();
                mHumidity.clear();
                mWindSpeed.clear();
                mPressure.clear();


                dayDate.setText(getDayOfWeek(currentWeatherLists.getCurrent().getDate()));
                dayDate.append(getMonth(currentWeatherLists.getCurrent().getDate()));

                mDayOrNight.add(currentWeatherLists.getCurrent().getDayOrNight());
                mLocationCurrentWeatherConditions.add(currentWeatherLists.getLocationAPI().getCityAndCountryName());

                // -----

                mCurrentTemperature.add(currentWeatherLists.getCurrent().getTemperatureCelcius());
                mMaxTemperature.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());

                Log.d(TAG, "onResponse: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());
                Log.d(TAG, "onResponse: " + mMaxTemperature.size());

                Log.d(TAG, "onResponse: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());

                mMinTemperature.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());
                mCurrentWeatherCondition.add(currentWeatherLists.getCurrent().getCondition().getWeatherCondition());
                mRealFeelTemp.add(currentWeatherLists.getCurrent().getFeelsLikeCelcius());
                mCurrentWeatherConditionIcon.add(currentWeatherLists.getCurrent().getCondition().getImageIconURL());

                // -----
                mSunriseTime.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getAstro().getSunrise());
                mSunsetTime.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getAstro().getSunset());
                mPrecipitation.add(currentWeatherLists.getCurrent().getPrecipMM());
                mHumidity.add(currentWeatherLists.getCurrent().getHumidity());
                mWindSpeed.add(currentWeatherLists.getCurrent().getWindSpeedKPH());
                mPressure.add(currentWeatherLists.getCurrent().getPressureMB());

                initRecyclerViewCurrentConditions();
                mSwipeRefreshLayout.setRefreshing(false);


            }

            public void onFailure(Call<CurrentWeatherList> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");

                } else {
                }
                mSwipeRefreshLayout.setRefreshing(false);

                Log.d(TAG, "Code: " + t.getMessage());

            }
        });
    }

    private void forecastConditions() {
        Call<GetForecast> call = JSONApiHolder.getForecast(getLocation(), 3);

        call.enqueue(new Callback<GetForecast>() {
            @Override
            public void onResponse(Call<GetForecast> call, Response<GetForecast> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "ForecastCode: " + response.code());
                    return;
                }

                GetForecast getForecastParent = response.body();

                List<forecastday> forecastdaysList = getForecastParent.getForecast().getForecastdayList();


                mDayOfTheWeek.clear();
                mMinTemp.clear();
                mMaxTemp.clear();
                mForecastIcon.clear();


//                Log.d(TAG, "onResponse: " + getDayOfWeek(year, month, day));

                for (forecastday post : forecastdaysList) {

//                    for(int i = 0; i < days; i++) {}


//                    Log.d(TAG, "onResponse: " + post.getDay().getMinTempC());
//                    Log.d(TAG, "onResponse: " + post.getDay().getMaxTempC());

                    mDayOfTheWeek.add(getDayOfWeek(post.getDate()));
                    mMinTemp.add(post.getDay().getMinTempC());
                    mMaxTemp.add(post.getDay().getMaxTempC());
                    mForecastIcon.add(post.getDay().getCondition().getImageIconURL());
                }

                if (getView() != null) {
                    initRecyclerViewForecast();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<GetForecast> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                } else {
                }
                Log.d(TAG, "Code: " + t.getMessage());

                if (getView() != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }

        });
    }

    public String getDayOfWeek(String date) {
        String yearS = date.substring(0, 4);
        String monthS = date.substring(5, 7);
        String dayS = date.substring(8, 10);

        String[] weekDays = new String[]{
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
        };

        int day = Integer.parseInt(dayS);
        int month = Integer.parseInt(monthS);
        int year = Integer.parseInt(yearS);

        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        int dayOfWeekInt = c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekString = weekDays[dayOfWeekInt - 1];

        return dayOfWeekString;
    }

    public String getMonth(String date) {
        String yearS = date.substring(0, 4);
        String monthS = date.substring(5, 7);
        String dayS = date.substring(8, 10);

        String[] months = new String[]{
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
        };

        int day = Integer.parseInt(dayS);
        int month = Integer.parseInt(monthS);
        int year = Integer.parseInt(yearS);

        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        int monthOfYearInt = c.get(Calendar.MONTH);
        String monthOfYearString = ", " + months[monthOfYearInt] + " " + dayS;

        return monthOfYearString;
    }


    // Location
    public String getLocation() {
        gpsLocation = new GPSLocation(getActivity());
        if (gpsLocation.canGetLocation()) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String latitudeS = String.valueOf(latitude);
            String longitudeS = String.valueOf(longitude);
            String latlong = latitudeS + "," + longitudeS;
            return latlong;
        } else {
            gpsLocation.showSettingsAlert();
            return "nothing";
        }
    }


    public void refreshFragment() {
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}