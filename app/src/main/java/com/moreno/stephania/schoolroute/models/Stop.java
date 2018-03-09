package com.moreno.stephania.schoolroute.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Representacion de la clase {@link Stop}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class Stop {

    /**
     *
     */
    @SerializedName("lat")
    @Expose
    private Double lat;

    /**
     *
     */
    @SerializedName("lng")
    @Expose
    private Double lng;

    /**
     *
     * @param v
     * @param v1
     */
    public Stop(double v, double v1) {
        this.lat = v;
        this.lng = v1;
    }

    /**
     *
     */
    public Stop() {
        return;
    }

    /**
     *
     * @return
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * @return
     */
    public Double setLat(Double lat) {
        this.lat = lat;
        return lat;
    }

    /**
     *
     * @return
     */
    public Double getLng() {
        return lng;
    }

    /**
     *
     * @param lng
     * @return
     */
    public Double setLng(Double lng) {
        this.lng = lng;
        return lng;
    }


}
