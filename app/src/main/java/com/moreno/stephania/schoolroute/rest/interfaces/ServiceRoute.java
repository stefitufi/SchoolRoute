package com.moreno.stephania.schoolroute.rest.interfaces;
import com.moreno.stephania.schoolroute.models.Buses;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interfaz que llama la Url del servicio web {@link Buses}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public interface ServiceRoute {

    @GET("/bins/10yg1t")
    Call<Buses> getRoute();
}
