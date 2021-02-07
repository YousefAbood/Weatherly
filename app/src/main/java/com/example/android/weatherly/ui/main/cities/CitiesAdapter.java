package com.example.android.weatherly.ui.main.cities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.weatherly.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitiesAdapter
        extends RecyclerView.Adapter<CitiesAdapter.ViewHolder>{

    private static final String TAG = "citiesAdapter";
    Context context;
    List<String> citiesList = Collections.emptyList();

    public CitiesAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<String> citiesList) {
        this.citiesList = citiesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cities_recyclerview_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesAdapter.ViewHolder holder, int position) {
        holder.cityName.setText(citiesList.get(position));
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.cityNameRVLayout);
        }
    }
}
