package com.example.android.weatherly.ui.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.weatherly.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ForecastDataAdapter
        extends RecyclerView.Adapter<ForecastDataAdapter.ViewHolder> {

    private static final String TAG = "forecastDataAdapter";
    private Context context;
    private ArrayList<String> dayOfTheWeek;
    private ArrayList<String> minTemp;
    private ArrayList<String> maxTemp;
    private ArrayList<String> forecastIcon;


    public ForecastDataAdapter(Context context, ArrayList<String> dayOfTheWeek, ArrayList<String> minTemp, ArrayList<String> maxTemp, ArrayList<String> currentWeatherConditionIcon) {
        this.context = context;
        this.dayOfTheWeek = dayOfTheWeek;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.forecastIcon = currentWeatherConditionIcon;

    }

    @NonNull
    @Override
    public ForecastDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastDataAdapter.ViewHolder holder, int position) {


        if(position == 0) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            position++; } else {

            Glide.with(context)
                    .asBitmap()
                    .load(forecastIcon.get((position)))
                    .into(holder.forecastIcon);

            holder.dayOfTheWeek.setText(dayOfTheWeek.get(position));
            holder.minTemp.setText(minTemp.get(position));
            holder.maxTemp.setText(maxTemp.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return minTemp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayOfTheWeek, maxTemp, minTemp;
        CircleImageView forecastIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dayOfTheWeek = itemView.findViewById(R.id.dayOfTheWeek);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            minTemp = itemView.findViewById(R.id.minTemp);
            forecastIcon = itemView.findViewById(R.id.current_weather_condition_icon);

        }

     }
}
