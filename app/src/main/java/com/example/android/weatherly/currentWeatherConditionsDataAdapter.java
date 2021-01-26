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
    public ArrayList<String> mDayOrNight;
    public ArrayList<String> mLocation;
    public ArrayList<String> mCurrentTemperature;
    public ArrayList<String> mMaxTemperature;
    public ArrayList<String> mMinTemperature;
    public ArrayList<String> mCurrentWeatherCondition;
    public ArrayList<String> mRealFeelTemp;
    public ArrayList<String> mCurrentWeatherConditionIcon;
    public ArrayList<String> mSunriseTime;
    public ArrayList<String> mSunsetTime;
    public ArrayList<String> mPrecipitation;
    public ArrayList<String> mHumidity;
    public ArrayList<String> mWindSpeed;
    public ArrayList<String> mPressure;


//    public currentWeatherConditionsDataAdapter(Context context,
//                                               ArrayList<String> mDayOrNight,
//                                               ArrayList<String> mLocation,
//                                               ArrayList<String> mCurrentTemperature,
//                                               ArrayList<String> mMaxTemperature,
//                                               ArrayList<String> mMinTemperature,
//                                               ArrayList<String> mCurrentWeatherCondition,
//                                               ArrayList<String> mRealFeelTemp,
//                                               ArrayList<String> mCurrentWeatherConditionIcon,
//                                               ArrayList<String> mSunriseTime,
//                                               ArrayList<String> mSunsetTime,
//                                               ArrayList<String> mPrecipitation,
//                                               ArrayList<String> mHumidity,
//                                               ArrayList<String> mWindSpeed,
//                                               ArrayList<String> mPressure) {
//        this.context = context;
//        this.mDayOrNight = mDayOrNight;
//        this.mLocation = mLocation;
//        this.mCurrentTemperature = mCurrentTemperature;
//        this.mMaxTemperature = mMaxTemperature;
//        this.mMinTemperature = mMinTemperature;
//        this.mCurrentWeatherCondition = mCurrentWeatherCondition;
//        this.mRealFeelTemp = mRealFeelTemp;
//        this.mCurrentWeatherConditionIcon = mCurrentWeatherConditionIcon;
//        this.mSunriseTime = mSunriseTime;
//        this.mSunsetTime = mSunsetTime;
//        this.mPrecipitation = mPrecipitation;
//        this.mHumidity = mHumidity;
//        this.mWindSpeed = mWindSpeed;
//        this.mPressure = mPressure;
//    }

    public currentWeatherConditionsDataAdapter(Context context,
                                               ArrayList<String> mDayOrNight,
                                               ArrayList<String> mLocationCurrentWeatherConditions,
                                               ArrayList<String> mCurrentTemperature,
                                               ArrayList<String> mMaxTemperature,
                                               ArrayList<String> mMinTemperature,
                                               ArrayList<String> mCurrentWeatherCondition,
                                               ArrayList<String> mCurrentWeatherConditionIcon,
                                               ArrayList<String> mRealFeelTemp,
                                               ArrayList<String> mSunriseTime,
                                               ArrayList<String> mSunsetTime,
                                               ArrayList<String> mPrecipitation,
                                               ArrayList<String> mHumidity,
                                               ArrayList<String> mWindSpeed,
                                               ArrayList<String> mPressure) {

        this.context = context;
        this.mDayOrNight = mDayOrNight;
        this.mLocation = mLocationCurrentWeatherConditions;
        this.mCurrentTemperature = mCurrentTemperature;
        this.mMaxTemperature = mMaxTemperature;
        this.mMinTemperature = mMinTemperature;
        this.mCurrentWeatherCondition = mCurrentWeatherCondition;
        this.mRealFeelTemp = mRealFeelTemp;
        this.mCurrentWeatherConditionIcon = mCurrentWeatherConditionIcon;
        this.mSunriseTime = mSunriseTime;
        this.mSunsetTime = mSunsetTime;
        this.mPrecipitation = mPrecipitation;
        this.mHumidity = mHumidity;
        this.mWindSpeed = mWindSpeed;
        this.mPressure = mPressure;
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


        holder.day_or_night.setText(mDayOrNight.get(position));
        holder.location.setText(mLocation.get(position));
        holder.currentTemperature.setText(mCurrentTemperature.get(position));
        Log.d(TAG, "onBindViewHolder: " + mMaxTemperature.size());
        holder.maxTemperature.setText(mMaxTemperature.get(position));
        holder.minTemperature.setText(mMinTemperature.get(position));
        holder.weatherCondition.setText(mCurrentWeatherCondition.get(position));
        holder.real_feel_temp.setText(mRealFeelTemp.get(position));

        // -----
        holder.sunrise.setText(mSunriseTime.get(position));
        holder.sunset.setText(mSunsetTime.get(position));
        holder.precipitation.setText(mPrecipitation.get(position));
        holder.humidity.setText(mHumidity.get(position));
        holder.wind_speed.setText(mWindSpeed.get(position));
        holder.pressure.setText(mPressure.get(position));






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

        TextView currentTemperature, weatherCondition, day_or_night, location, real_feel_temp, minTemperature, maxTemperature, sunrise, sunset, precipitation, humidity, wind_speed, pressure;
        CircleImageView current_weather_condition_icon;
        ConstraintLayout parentLayoutCurrentWeatherConditions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connecting ID of the Current Weather Conditions Layout components with the Adapter to inflate them
            parentLayoutCurrentWeatherConditions = itemView.findViewById(R.id.parentLayoutCurrentWeatherConditions);
            day_or_night = itemView.findViewById(R.id.day_or_night);
            location = itemView.findViewById(R.id.locationAPI);
            currentTemperature = itemView.findViewById(R.id.currentTemperature);
            maxTemperature = itemView.findViewById(R.id.maxTempCurrentWeatherConditionsLayout);
            minTemperature = itemView.findViewById(R.id.minTempCurrentWeatherConditionsLayout);
            weatherCondition = itemView.findViewById(R.id.weatherCondition);
            real_feel_temp = itemView.findViewById(R.id.real_feel_temp);
            current_weather_condition_icon = itemView.findViewById(R.id.current_weather_condition_icon);

            // -----

            sunrise = itemView.findViewById(R.id.sunriseTime);
            sunset = itemView.findViewById(R.id.sunsetTime);
            precipitation = itemView.findViewById(R.id.precipitationNumber);
            humidity = itemView.findViewById(R.id.humidity);
            wind_speed = itemView.findViewById(R.id.wind_speed);
            pressure = itemView.findViewById(R.id.pressureHPA);
            wind_speed = itemView.findViewById(R.id.wind_speed);
            humidity = itemView.findViewById(R.id.humidity);
        }
    }

}
