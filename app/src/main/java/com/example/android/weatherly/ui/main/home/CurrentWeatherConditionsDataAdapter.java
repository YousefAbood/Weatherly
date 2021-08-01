package com.example.android.weatherly.ui.main.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.weatherly.R;
import com.example.android.weatherly.data.model.CurrentWeatherList.CurrentWeatherList;
import com.example.android.weatherly.data.model.Forecast.GetForecast;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentWeatherConditionsDataAdapter
        extends RecyclerView.Adapter<CurrentWeatherConditionsDataAdapter.ViewHolder> {

    private static final String TAG = "currentWeatherAdapter";
    private final Context context;
    private MutableLiveData<GetForecast> forecastMutableLiveData = new MutableLiveData<>();


    public CurrentWeatherConditionsDataAdapter(Context context) {
        this.context = context;
    }

    public void updateData(MutableLiveData<GetForecast> forecastMutableLiveData) {
        this.forecastMutableLiveData = forecastMutableLiveData;
        notifyDataSetChanged();
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

        GetForecast getForecast = forecastMutableLiveData.getValue();



        Glide.with(context)
                .asBitmap()
                .load("https:" + getForecast.getCurrent().getCondition().getIcon())
                .into(holder.current_weather_condition_icon);




        holder.location.setText(getForecast.getLocation().getName() + ", " + getForecast.getLocation().getCountry());
        holder.currentTemperature.setText(String.valueOf(getForecast.getCurrent().getTempC()));
//        Log.d(TAG, "onBindViewHolder: " + mMaxTemperature.size());

        holder.minTemperature.setText(String.valueOf(getForecast.getForecast().getForecastday().get(position).getDay().getMintempC()));
        holder.maxTemperature.setText(String.valueOf(getForecast.getForecast().getForecastday().get(position).getDay().getMaxtempC()));

        holder.weatherCondition.setText(getForecast.getCurrent().getCondition().getText());
        holder.real_feel_temp.setText(String.valueOf(getForecast.getCurrent().getFeelslikeC()));

        // -----
        holder.sunrise.setText(getForecast.getForecast().getForecastday().get(position).getAstro().getSunrise());
        holder.sunset.setText(getForecast.getForecast().getForecastday().get(position).getAstro().getSunset());
        holder.precipitation.setText(getForecast.getCurrent().getPrecipMm() + "%");
        holder.humidity.setText(getForecast.getCurrent().getHumidity() + "%");
        holder.wind_speed.setText(getForecast.getCurrent().getWindKph() + " km/h");
        holder.pressure.setText(getForecast.getCurrent().getPressureMb() + " hPa");






        holder.parentLayoutCurrentWeatherConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView currentTemperature, weatherCondition, location, real_feel_temp, minTemperature, maxTemperature, sunrise, sunset, precipitation, humidity, wind_speed, pressure;
        CircleImageView current_weather_condition_icon;
        ConstraintLayout parentLayoutCurrentWeatherConditions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connecting ID of the Current Weather Conditions Layout components with the Adapter to inflate them
            parentLayoutCurrentWeatherConditions = itemView.findViewById(R.id.parentLayoutCurrentWeatherConditions);
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
