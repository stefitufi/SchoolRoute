package com.moreno.stephania.schoolroute.rest;
import com.moreno.stephania.schoolroute.rest.interfaces.ServiceRoute;
import com.moreno.stephania.schoolroute.rest.interfaces.ServiceStop;
import com.moreno.stephania.schoolroute.utils.ConstantUtil;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase que realiza peticion al servidor
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class RestClient {

    /** Variable de tipo {@link Retrofit} **/
    private static Retrofit mRetrofit = null;

    /**
     * Metodo que hace el llamado al servivio {@link ServiceRoute}
     *
     * @return Url
     */
    public static ServiceRoute getServiceRoute() {
        return RestClient.getClient(ConstantUtil.BASEURL).create(ServiceRoute.class);
    }

    /**
     * Metodo que hace el llamado al servivio {@link ServiceRoute}
     *
     * @return Url
     */
    public static ServiceStop getServiceStop()
    {
        return RestClient.getClient(ConstantUtil.BASEURL).create(ServiceStop.class);
    }

    /**
     * Metodo que realiza peticion al Web Service
     *
     * @return Informcion del usuario
     */
    public static Retrofit getClient(String baseUrl) {
        if (mRetrofit ==null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

}
