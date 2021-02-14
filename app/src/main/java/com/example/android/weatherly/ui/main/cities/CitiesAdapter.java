package com.example.android.weatherly.ui.main.cities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.weatherly.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitiesAdapter
        extends RecyclerView.Adapter<CitiesAdapter.ViewHolder>{

    private final onItemClickedListenerCities onItemClicked;
    private static final String TAG = "citiesAdapter";
    Context context;
    List<String> citiesList = Collections.emptyList();
    public ArrayList<String> mCurrentWeatherConditionsCities = new ArrayList<>();
    public ArrayList<String> mCurrentWeatherConditionIconCities = new ArrayList<>();
    public ArrayList<String> mMinTemperatureCities = new ArrayList<>();
    public ArrayList<String> mMaxTemperatureCities = new ArrayList<>();
    public ArrayList<String> mPrecipitaionCities = new ArrayList<>();
    public ArrayList<String> mWindSpeedCities = new ArrayList<>();
    public ArrayList<String> mHumidityCities = new ArrayList<>();
    public ArrayList<String> mPressureCities = new ArrayList<>();



    public CitiesAdapter(Context context, onItemClickedListenerCities onItemClicked) {
        this.context = context;
        this.onItemClicked = onItemClicked;
    }

    public void updateData(List<String> citiesList, ArrayList<String> mCurrentWeatherConditionsCities, ArrayList<String> mCurrentWeatherConditionIconCities, ArrayList<String> mMinTemperatureCities, ArrayList<String> mMaxTemperatureCities, ArrayList<String> mPrecipitaionCities, ArrayList<String> mWindSpeedCities, ArrayList<String> mHumidityCities, ArrayList<String> mPressureCities) {
        this.citiesList = citiesList;
        this.mCurrentWeatherConditionsCities = mCurrentWeatherConditionsCities;
        this.mCurrentWeatherConditionIconCities = mCurrentWeatherConditionIconCities;
        this.mMinTemperatureCities = mMinTemperatureCities;
        this.mMaxTemperatureCities = mMaxTemperatureCities;
        this.mPrecipitaionCities = mPrecipitaionCities;
        this.mWindSpeedCities = mWindSpeedCities;
        this.mHumidityCities = mHumidityCities;
        this.mPressureCities = mPressureCities;
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
        Glide.with(context)
                .asBitmap()
                .load(mCurrentWeatherConditionIconCities.get((position)))
                .into(holder.currentWeatherConditionCitiesIcon);

        holder.cityName.setText(citiesList.get(position));
        holder.currentWeatherConditionCityTV.setText(mCurrentWeatherConditionsCities.get(position));
        holder.minTemperatureCities.setText(mMinTemperatureCities.get(position));
        holder.maxTemperatureCities.setText(mMaxTemperatureCities.get(position));
        holder.precipitationPercentageCities.setText(mPrecipitaionCities.get(position));
        holder.windSpeedNumberCities.setText(mWindSpeedCities.get(position));
        holder.humidityCityPercentage.setText(mHumidityCities.get(position));
        holder.pressureCityNumber.setText(mPressureCities .get(position));

        holder.citiesRVLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCity = citiesList.get(position);
                Log.d(TAG, "onClick: " + selectedCity);
                onItemClicked.onCityClicked(selectedCity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCurrentWeatherConditionsCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityName, currentWeatherConditionCityTV, maxTemperatureCities, minTemperatureCities, precipitationPercentageCities, windSpeedNumberCities, pressureCityNumber, humidityCityPercentage;
        CircleImageView currentWeatherConditionCitiesIcon;
        ConstraintLayout citiesRVLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            citiesRVLayout = itemView.findViewById(R.id.citiesRVLayout);
            cityName = itemView.findViewById(R.id.cityNameRVLayout);
            currentWeatherConditionCityTV = itemView.findViewById(R.id.currentWeatherConditionsCityTV);
            currentWeatherConditionCitiesIcon = itemView.findViewById(R.id.currentWeatherConditionsCitiesIcon);
            maxTemperatureCities = itemView.findViewById(R.id.maxTemperatureCities);
            minTemperatureCities = itemView.findViewById(R.id.minTemperatureCities);
            precipitationPercentageCities = itemView.findViewById(R.id.precipitationPercentageCities);
            windSpeedNumberCities = itemView.findViewById(R.id.windSpeedNumberCities);
            pressureCityNumber = itemView.findViewById(R.id.pressureCityNumber);
            humidityCityPercentage = itemView.findViewById(R.id.humidityCityPercentage);



        }
    }

    public static interface onItemClickedListenerCities {
        void onCityClicked(String cityName);
    }


}
