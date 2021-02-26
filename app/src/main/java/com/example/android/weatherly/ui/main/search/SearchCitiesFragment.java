package com.example.android.weatherly.ui.main.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.android.weatherly.R;
import com.example.android.weatherly.data.api.JSONApiHolder;
import com.example.android.weatherly.data.model.searchLocation;
import com.example.android.weatherly.ui.main.MainViewModel;
import com.example.android.weatherly.ui.main.cities.CitiesFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchCitiesFragment
        extends Fragment
        implements SearchCitiesAdapter.OnItemClickedListener {
    public SearchCitiesFragment() {
        super(R.layout.fragment_search_cities);
    }

    private static final String TAG = "searchCitiesFragment";

    private MutableLiveData<List<String>> mSearchViewResult = new MutableLiveData<>(Collections.emptyList());

    private MainViewModel mainViewModel;

    private AlertDialog alertDialog = null;

    private com.example.android.weatherly.data.api.JSONApiHolder JSONApiHolder;
    SearchView searchViewCities;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RetroFit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONApiHolder = retrofit.create(JSONApiHolder.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        searchViewCities = (SearchView) view.findViewById(R.id.searchViewCities);

        searchViewCities.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if ("".equals(query)) {
                    mSearchViewResult.setValue(Collections.emptyList());
                }
                getSearchCityResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                if ("".equals(newText)) {
                    mSearchViewResult.setValue(Collections.emptyList());
                }
                getSearchCityResults(newText);
                return false;
            }

        });


        initRecyclerViewSearchResults();
    }

    private void initRecyclerViewSearchResults() {
        RecyclerView recyclerViewSearchResults = getView().findViewById(R.id.recyclerViewSearch);
        SearchCitiesAdapter adapter = new SearchCitiesAdapter(getActivity(), this);
        recyclerViewSearchResults.setAdapter(adapter);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Dividers for RecyclerView

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));

        recyclerViewSearchResults.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        mSearchViewResult.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.updateData(strings);
            }
        });
    }

    private void getSearchCityResults(String cityName) {
        Call<List<searchLocation>> call = JSONApiHolder.getSearchLocation(cityName);

        call.enqueue(new Callback<List<searchLocation>>() {
            @Override
            public void onResponse(Call<List<searchLocation>> call, Response<List<searchLocation>> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                mSearchViewResult.setValue(Collections.emptyList());

                List<searchLocation> searchLocationList = response.body();

                List<String> cityNames = new ArrayList<>(searchLocationList.size());

                for(searchLocation post : searchLocationList) {
                    cityNames.add(post.getSearchLocationName());
                }

                mSearchViewResult.setValue(Collections.unmodifiableList(cityNames));
            }

            @Override
            public void onFailure(Call<List<searchLocation>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");
                }

                Log.d(TAG, "Code: " + t.getMessage());
            }
        });
    }

    @Override
    public void onCityClicked(String cityName) {
        alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle(cityName)
                .setMessage("Do you want to add '" + cityName + "'?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainViewModel.addCityName(cityName);
                        alertDialog = null;
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog = null;
                        dialog.dismiss();
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
        Fragment fragment = new SearchCitiesFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}