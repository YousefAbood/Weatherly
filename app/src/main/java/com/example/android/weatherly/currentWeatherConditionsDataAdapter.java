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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class currentWeatherConditionsDataAdapter extends RecyclerView.Adapter<currentWeatherConditionsDataAdapter.ViewHolder> {

    private static final String TAG = "currentWeatherAdapter";
    private Context context;
    public ArrayList<String> mCurrentTemperature;
    public ArrayList<String> mCurrentWeatherCondition;
    public ArrayList<String> mDayOrNight;
    public ArrayList<String> mLocation;
    public ArrayList<String> mRealFeelTemp;
    public ArrayList<String> mWindSpeed;
    public ArrayList<String> mHumidity;
    public ArrayList<String> mCurrentWeatherConditionIcon;


    public currentWeatherConditionsDataAdapter(Context context, ArrayList<String> mCurrentTemperature, ArrayList<String> mCurrentWeatherCondition, ArrayList<String> mDayOrNight, ArrayList<String> mLocation, ArrayList<String> mRealFeelTemp, ArrayList<String> mWindSpeed, ArrayList<String> mHumidity, ArrayList<String> mCurrentWeatherConditionIcon) {
        this.context = context;
        this.mCurrentTemperature = mCurrentTemperature;
        this.mCurrentWeatherCondition = mCurrentWeatherCondition;
        this.mDayOrNight = mDayOrNight;
        this.mLocation = mLocation;
        this.mRealFeelTemp = mRealFeelTemp;
        this.mWindSpeed = mWindSpeed;
        this.mHumidity = mHumidity;
        this.mCurrentWeatherConditionIcon = mCurrentWeatherConditionIcon;
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

        Glide.with(context)
                .asBitmap()
                .load(mCurrentWeatherConditionIcon.get((position)))
                .into(holder.current_weather_condition_icon);


        holder.currentTemperature.setText(mCurrentTemperature.get(position));
        holder.weatherCondition.setText(mCurrentWeatherCondition.get(position));
        holder.day_or_night.setText(mDayOrNight.get(position));
        holder.location.setText(mLocation.get(position));
        holder.real_feel_temp.setText(mRealFeelTemp.get(position));
        holder.wind_speed.setText(mWindSpeed.get(position));
        holder.humidity.setText(mHumidity.get(position));





        holder.parentLayoutCurrentWeatherConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCurrentTemperature.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView currentTemperature, weatherCondition, day_or_night, location, real_feel_temp, wind_speed, humidity;
        CircleImageView current_weather_condition_icon;
        ConstraintLayout parentLayoutCurrentWeatherConditions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connecting ID of the Current Weather Conditions Layout components with the Adapter to inflate them
            parentLayoutCurrentWeatherConditions = itemView.findViewById(R.id.parentLayoutCurrentWeatherConditions);
            currentTemperature = itemView.findViewById(R.id.currentTemperature);
            weatherCondition = itemView.findViewById(R.id.weatherCondition);
            day_or_night = itemView.findViewById(R.id.day_or_night);
            location = itemView.findViewById(R.id.locationAPI);
            real_feel_temp = itemView.findViewById(R.id.real_feel_temp);
            wind_speed = itemView.findViewById(R.id.wind_speed);
            humidity = itemView.findViewById(R.id.humidity);
            current_weather_condition_icon = itemView.findViewById(R.id.current_weather_condition_icon);
        }
    }

}
