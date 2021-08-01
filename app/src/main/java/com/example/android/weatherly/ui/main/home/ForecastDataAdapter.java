package com.example.android.weatherly.ui.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.weatherly.R;
import com.example.android.weatherly.data.model.Forecast.GetForecast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ForecastDataAdapter
        extends RecyclerView.Adapter<ForecastDataAdapter.ViewHolder> {

    private static final String TAG = "forecastDataAdapter";
    private Context context;
    private MutableLiveData<GetForecast> forecastMutableLiveData = new MutableLiveData<>();




    public ForecastDataAdapter(Context context) {
        this.context = context;

    }

    public void updateData(MutableLiveData<GetForecast> forecastMutableLiveData) {
        this.forecastMutableLiveData = forecastMutableLiveData;
        notifyDataSetChanged();
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

        GetForecast getForecast = forecastMutableLiveData.getValue();
        HomeFragment homeFragment = new HomeFragment();


        if(position == 0) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            position++; } else {

            Glide.with(context)
                    .asBitmap()
                    .load("https:" + getForecast.getForecast().getForecastday().get(position).getDay().getCondition().getIcon())
                    .into(holder.forecastIcon);

            holder.dayOfTheWeek.setText(homeFragment.getDayOfWeek(getForecast.getForecast().getForecastday().get(position).getDate()));
            holder.minTemp.setText(String.valueOf(getForecast.getForecast().getForecastday().get(position).getDay().getMintempC()));
            holder.maxTemp.setText(String.valueOf(getForecast.getForecast().getForecastday().get(position).getDay().getMaxtempC()));
        }

    }

    @Override
    public int getItemCount() {
        return 3;
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
