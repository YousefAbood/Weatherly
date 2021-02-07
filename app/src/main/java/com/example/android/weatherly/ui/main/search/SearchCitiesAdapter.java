package com.example.android.weatherly.ui.main.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.weatherly.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class SearchCitiesAdapter
        extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {
    private final OnItemClickedListener onItemClickedListener;
    private List<String> searchViewResult = Collections.emptyList();

    private static final String TAG = "searchCitiesAdapter";
    Context context;

    public SearchCitiesAdapter(Context context, OnItemClickedListener OnItemClickedListener) {
        this.context = context;
        this.onItemClickedListener = OnItemClickedListener;
    }

    public void updateData(List<String> searchViewResult) {
        this.searchViewResult = searchViewResult;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchCitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_cities_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(searchViewResult.get(position));
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

        public void bind(String name) {
            searchViewResult.setText(name);

            searchViewResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickedListener.onCityClicked(name);
                }
            });
        }
    }

    public static interface OnItemClickedListener {
        void onCityClicked(String cityName);
    }
}
