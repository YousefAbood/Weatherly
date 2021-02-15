package com.example.android.weatherly.ui.main.cities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.example.android.weatherly.R;
import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.CurrentWeatherList;
import com.example.android.weatherly.itemDecorator.SpacingItemDecorator;
import com.example.android.weatherly.ui.main.MainViewModel;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CitiesFragment
        extends Fragment
        implements CitiesAdapter.onItemClickedListenerCities{
    public CitiesFragment() {
        super(R.layout.fragment_cities);
    }

    private static final String TAG = "CitiesFragment";
    // This fragment will contain the Cities added after clicking on Add Cities, Going to searchLocationFragment, Clicking on the city, Prompting whether you want add the city or not
    // If Yes then you add it to an adapter thats attached to a RecyclerView in citiesFragment,
    // TO-DO List
    // RetroFit Call for Search
    // RecyclerView & Adapter for Added Cities
    // RecyclerView & Adapter for SearchView

    private AlertDialog alertDialog = null;

    CitiesAdapter adapter;


    JSONApiHolder JSONApiHolder;

    public ArrayList<String> mCountryName = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionsCitiesFrag = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionIconCitiesFrag = new ArrayList<>();
    public ArrayList<String> mMinTemperatureCitiesFrag = new ArrayList<>();
    public ArrayList<String> mMaxTemperatureCitiesFrag = new ArrayList<>();
    public ArrayList<String> mPrecipitaionCitiesFrag = new ArrayList<>();
    public ArrayList<String> mWindSpeedCitiesFrag = new ArrayList<>();
    public ArrayList<String> mHumidityCitiesFrag = new ArrayList<>();
    public ArrayList<String> mPressureCitiesFrag = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(com.example.android.weatherly.data.api.JSONApiHolder.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.citiesRecyclerView);

        adapter = new CitiesAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(45);
        recyclerView.addItemDecoration(itemDecorator);


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(view.findViewById(R.id.toolbarCities));

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.getCityNames().observe(getViewLifecycleOwner(), (data) -> {


            Log.d(TAG, "onViewCreated: -------------------------------");

            for(int i = 0; i < data.size(); i++) {


                Call<CurrentWeatherList> call = JSONApiHolder.getCurrentWeatherList(data.get(i), 1);

//        Log.d(TAG, "onViewCreated: " + data.get(i));
                call.enqueue(new Callback<CurrentWeatherList>() {




                    @Override
                    public void onResponse(Call<CurrentWeatherList> call, Response<CurrentWeatherList> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "City Adapter Response Code: " + response.code());
                            return;
                        }



                        CurrentWeatherList currentWeatherLists = response.body();

                        currentWeatherLists.getLocationAPI().getCityAndCountryName();

                        Log.d(TAG, "onViewCreated: " + mCurrentWeatherConditionIconCitiesFrag.size());


                        mCountryName.add(currentWeatherLists.getLocationAPI().getCityAndCountryName());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getLocationAPI().getCityAndCountryName());

                        mCurrentWeatherConditionsCitiesFrag.add(currentWeatherLists.getCurrent().getCondition().getWeatherCondition());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getCondition().getWeatherCondition());

                        mCurrentWeatherConditionIconCitiesFrag.add(currentWeatherLists.getCurrent().getCondition().getImageIconURL());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getCondition().getImageIconURL());

                        mMaxTemperatureCitiesFrag.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMaxTempC());

                        mMinTemperatureCitiesFrag.add(currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getForecast().getForecastdayList().get(0).getDay().getMinTempC());

                        mPrecipitaionCitiesFrag.add(currentWeatherLists.getCurrent().getPrecipMM());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getPrecipMM());

                        mWindSpeedCitiesFrag.add(currentWeatherLists.getCurrent().getWindSpeedKPH());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getWindSpeedKPH());

                        mHumidityCitiesFrag.add(currentWeatherLists.getCurrent().getHumidity());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getHumidity());

                        mPressureCitiesFrag.add(currentWeatherLists.getCurrent().getPressureMB());
                        Log.d(TAG, "onViewCreated: " + currentWeatherLists.getCurrent().getPressureMB());


                        Log.d(TAG, "onViewCreated: -------------------------------");
                        adapter.updateData(mCountryName, mCurrentWeatherConditionsCitiesFrag, mCurrentWeatherConditionIconCitiesFrag, mMinTemperatureCitiesFrag, mMaxTemperatureCitiesFrag, mPrecipitaionCitiesFrag, mWindSpeedCitiesFrag, mHumidityCitiesFrag, mPressureCitiesFrag);
                        Log.d(TAG, "onViewCreated: " + "finished");
                    }

                    @Override
                    public void onFailure(Call<CurrentWeatherList> call, Throwable t) {
                        if (t instanceof IOException) {
                            Log.e(TAG, "onFailure: ");
                        }

                        Log.d(TAG, "Code: " + t.getMessage());
                    }


                    //noinspection Convert2MethodRef

                });
            }
        });


    }


    @Override
    public void onCityClicked(String cityName) {
        Log.d(TAG, "onCityClicked: " + cityName);
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle(cityName)
                .setMessage("Do you want to remove '" + cityName + "from list of Cities?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainViewModel.removeCityName(cityName);
                        alertDialog = null;
                        refreshFragment();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog = null;
                        dialog.dismiss();
                        onDestroyView();
                    }
                })
                .setCancelable(false)
                .create();

        alertDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }


    public void refreshFragment() {
        Fragment fragment = new CitiesFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        refreshFragment();
//    }
}