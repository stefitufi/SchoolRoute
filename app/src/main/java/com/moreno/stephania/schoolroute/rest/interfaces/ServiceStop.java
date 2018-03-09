package com.moreno.stephania.schoolroute.rest.interfaces;
import com.moreno.stephania.schoolroute.models.Stops;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interfaz que llama la Url del servicio web {@link Stops}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public interface ServiceStop {

    @GET("{input}")
    Call<Stops> getAnswers(@Path("input") String mInput);
}
