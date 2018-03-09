package com.moreno.stephania.schoolroute.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Representacion de la clase {@link Buses}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class Buses {

    /**
     *
     */
    @SerializedName("response")
    @Expose
    private Boolean response;

    /**
     *
     */
    @SerializedName("school_buses")
    @Expose
    private List<SchoolBus> schoolBuses = null;

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
     *
     * @return
     */
    public List<SchoolBus> getSchoolBuses() {
        return schoolBuses;
    }

    /**
     *
     * @param schoolBuses
     */
    public void setSchoolBuses(List<SchoolBus> schoolBuses) {
        this.schoolBuses = schoolBuses;
    }
}
