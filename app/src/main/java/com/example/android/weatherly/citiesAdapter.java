package com.example.android.weatherly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class citiesAdapter extends RecyclerView.Adapter<citiesAdapter.ViewHolder>{

    private static final String TAG = "citiesAdapter";
    Context context;
    ArrayList<String> citiesList;

    public citiesAdapter(Context context, ArrayList<String> citiesList) {
        this.context = context;
        this.citiesList = citiesList;
    }

    @NonNull
    @Override
    public citiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cities_recyclerview_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull citiesAdapter.ViewHolder holder, int position) {
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
