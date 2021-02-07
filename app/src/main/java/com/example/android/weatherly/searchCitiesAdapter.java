package com.example.android.weatherly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class searchCitiesAdapter extends RecyclerView.Adapter<searchCitiesAdapter.ViewHolder> {

    private static final String TAG = "searchCitiesAdapter";
    Context context;
    ArrayList<String> searchViewResult;
    getInformation getInfo;

    public searchCitiesAdapter(Context context, ArrayList<String> searchViewResult) {
        this.context = context;
        this.searchViewResult = searchViewResult;
    }

    @NonNull
    @Override
    public searchCitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_cities_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.searchViewResult.setText(searchViewResult.get(position));

        holder.searchViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchViewResult.get(position);
                Bundle args = new Bundle();
                args.putString("cityName", cityName);
                citiesFragment fragment = new citiesFragment();
                fragment.setArguments(args);

                Fragment frag = new citiesFragment();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).addToBackStack(null).commit();

                Log.d(TAG, "onClick: " + searchViewResult.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return searchViewResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView searchViewResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            searchViewResult = itemView.findViewById(R.id.searchViewResult);
        }

    }


}
