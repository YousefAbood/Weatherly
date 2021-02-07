package com.example.android.weatherly;

import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    JSONApiHolder JSONApiHolderRetrofit;

    public Retrofit(String baseUrl) {
        retrofit2.Retrofit retrofit = new Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       JSONApiHolderRetrofit = retrofit.create(JSONApiHolder.class);
    }

    public JSONApiHolder getJSONApiHolderRetrofit() {
        return JSONApiHolderRetrofit;
    }
}
