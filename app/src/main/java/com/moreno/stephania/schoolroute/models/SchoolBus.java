package com.moreno.stephania.schoolroute.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Representacion de la clase {@link SchoolBus}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class SchoolBus implements Serializable {

    /**
     *
     */
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     *
     */
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     */
    @SerializedName("description")
    @Expose
    private String description;

    /**
     *
     */
    @SerializedName("stops_url")
    @Expose
    private String stopsUrl;

    /**
     *
     */
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getStopsUrl() {
        return stopsUrl;
    }

    /**
     *
     * @param stopsUrl
     */
    public void setStopsUrl(String stopsUrl) {
        this.stopsUrl = stopsUrl;
    }

    /**
     *
     * @return
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
