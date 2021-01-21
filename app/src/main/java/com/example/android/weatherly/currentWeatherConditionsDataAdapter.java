package com.example.android.weatherly;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class currentWeatherConditionsDataAdapter extends RecyclerView.Adapter<currentWeatherConditionsDataAdapter.ViewHolder>{

    private static final String TAG = "currentWeatherAdapter";
    private Context context;
    public  ArrayList<String> mCurrentWeatherConditions;
    public  ArrayList<String> mCountry;
    public  ArrayList<String> mCity;
    Location location = new Location();




    public currentWeatherConditionsDataAdapter(Context context, ArrayList<String> mCurrentWeatherConditions, ArrayList<String> mCountry, ArrayList<String> mCity) {
        this.context = context;
        this.mCurrentWeatherConditions = mCurrentWeatherConditions;
        this.mCountry = mCountry;
        this.mCity = mCity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_weather_conditions_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.current_weather_conditions.setText(mCurrentWeatherConditions.get(position));


        Log.d(TAG, "onBindViewHolder: " + mCurrentWeatherConditions.get(position));



        holder.parentLayoutCurrentWeatherConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCurrentWeatherConditions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView current_weather_conditions;
        ConstraintLayout parentLayoutCurrentWeatherConditions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connecting ID of the Current Weather Conditions Layout components with the Adapter to inflate them
            current_weather_conditions = itemView.findViewById(R.id.currentTemperature);
            parentLayoutCurrentWeatherConditions = itemView.findViewById(R.id.parentLayoutCurrentWeatherConditions);
        }
    }

}
