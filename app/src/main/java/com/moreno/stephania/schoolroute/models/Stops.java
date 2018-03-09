package com.moreno.stephania.schoolroute.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Representacion de la clase {@link Stops}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class Stops implements Serializable {

    /** Boolean que contiente la respuesta de la peticion **/
    @SerializedName("response")
    @Expose
    private Boolean response;

    /** Variable de tipo lista {@link Stop} **/
    @SerializedName("stops")
    @Expose
    private List<Stop> stops = null;

    /** Entero que contiene la duracion del servidor **/
    @SerializedName("estimated_time_milliseconds")
    @Expose
    private Integer estimatedTimeMilliseconds;

    /** Entero que contiene la duracion del servidor **/
    @SerializedName("retry_time_milliseconds")
    @Expose
    private Integer retryTimeMilliseconds;

    /**
     *
     * @return
     */
    public Boolean getResponse() {
        return response;
    }

    /**
     *
     * @param response
     */
    public void setResponse(Boolean response) {
        this.response = response;
    }

    /**
     * Lista de tipo {@link Stop}
     * @return Lista de tipo stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     *
     * @param stops
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     *
     * @return
     */
    public Integer getEstimatedTimeMilliseconds() {
        return estimatedTimeMilliseconds;
    }

    /**
     *
     * @param estimatedTimeMilliseconds
     */
    public void setEstimatedTimeMilliseconds(Integer estimatedTimeMilliseconds) {
        this.estimatedTimeMilliseconds = estimatedTimeMilliseconds;
    }

    /**
     *
     * @return
     */
    public Integer getRetryTimeMilliseconds() {
        return retryTimeMilliseconds;
    }

    /**
     *
     * @param retryTimeMilliseconds
     */
    public void setRetryTimeMilliseconds(Integer retryTimeMilliseconds) {
        this.retryTimeMilliseconds = retryTimeMilliseconds;
    }

}
