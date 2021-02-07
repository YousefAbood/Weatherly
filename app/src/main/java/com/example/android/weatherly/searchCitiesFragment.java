package com.example.android.weatherly;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class searchCitiesFragment extends Fragment {

    private static final String TAG = "searchCitiesFragment";
    private ArrayList<String> mSearchViewResult = new ArrayList<>();
    private JSONApiHolder JSONApiHolder;
    SearchView searchViewCities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_cities, container, false);


        searchViewCities = (SearchView) rootView.findViewById(R.id.searchViewCities);

        searchViewCities.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(query == ""){
                    mSearchViewResult.clear();
                }
                getSearchCityResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                getSearchCityResults(newText);
                return false;
            }

        });


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RetroFit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApiHolder = retrofit.create(JSONApiHolder.class);


        // OnBackPressedCallback

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Fragment citiesFrag = new citiesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        citiesFrag).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // Search View


    }

    private void initRecyclerViewSearchResults() {
        RecyclerView recyclerViewSearchResults = getView().findViewById(R.id.recyclerViewSearch);
        searchCitiesAdapter adapter = new searchCitiesAdapter(getActivity(), mSearchViewResult);
        recyclerViewSearchResults.setAdapter(adapter);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Dividers for RecyclerView

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));

        recyclerViewSearchResults.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
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

                mSearchViewResult.clear();

                List<searchLocation> searchLocationList = response.body();


                for(searchLocation post : searchLocationList) {

                    mSearchViewResult.add(post.getSearchLocationName());
                }



                initRecyclerViewSearchResults();
            }

            @Override
            public void onFailure(Call<List<searchLocation>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ");

                } else {
                }

                Log.d(TAG, "Code: " + t.getMessage());

            }
        });
    }

//    public void goToCities() {
//        citiesFragment citiesFrag = new citiesFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                citiesFrag).commit();
//    }

}