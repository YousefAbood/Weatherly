package com.example.android.weatherly;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONApiHolder {

    @GET("currentconditions/v1/" + "{locationKey}" + "?apikey=JGIoaeeAvMuwL703QaUrLV8SWXr99j7D&details=true")
    Call<List<CurrentWeatherList>> getPosts(@Path("locationKey") int locationKey);

    @GET("locations/v1/" + "{locationKey}" + "?apikey=JGIoaeeAvMuwL703QaUrLV8SWXr99j7D")
    Call<Location> getLocation(@Path("locationKey") int locationKey);

}
