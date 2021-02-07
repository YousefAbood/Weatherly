package com.example.android.weatherly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxTempC;

    @SerializedName("maxtemp_f")
    @Expose
    private Double maxTempF;

    @SerializedName("mintemp_c")
    @Expose
    private Double minTempC;

    @SerializedName("mintemp_f")
    @Expose
    private Double minTempF;

    @SerializedName("avgtemp_c")
    @Expose
    private Double avgTempC;

    @SerializedName("avgtemp_f")
    @Expose
    private Double avgTempF;

    @SerializedName("avghumidity")
    @Expose
    private Double avgHumidity;

    @SerializedName("condition")
    @Expose
    public Condition condition;

    public String getMaxTempC() {
        String temperatureCel = String.valueOf(maxTempC) + "°";
        return temperatureCel;
    }

    public String getMaxTempF() {
        String temperatureCel = String.valueOf(maxTempF) + "°";
        return temperatureCel;
    }

    public String getMinTempC() {
        String temperatureCel = String.valueOf(minTempC) + "°";
        return temperatureCel;
    }

    public String getMinTempF() {
        String temperatureCel = String.valueOf(maxTempF) + "°";
        return temperatureCel;
    }

    public String getAvgTempC() {
        String temperatureCel = String.valueOf(avgTempC) + "°";
        return temperatureCel;
    }

    public String getAvgTempF() {
        String temperatureCel = String.valueOf(avgTempF) + "°";
        return temperatureCel;
    }

    public String getAvgHumidity() {
        String temperatureCel = String.valueOf(avgHumidity) + "%";
        return temperatureCel;
    }

    public Condition getCondition() {
        return condition;
    }


}
