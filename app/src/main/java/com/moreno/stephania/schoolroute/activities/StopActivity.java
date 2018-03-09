package com.moreno.stephania.schoolroute.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moreno.stephania.schoolroute.R;
import com.moreno.stephania.schoolroute.models.Stop;
import com.moreno.stephania.schoolroute.models.Stops;
import com.moreno.stephania.schoolroute.rest.RestClient;
import com.moreno.stephania.schoolroute.rest.interfaces.ServiceStop;
import com.moreno.stephania.schoolroute.utils.ConstantUtil;
import com.moreno.stephania.schoolroute.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Actividad que muestra el mapa con al rutas asignadas
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class StopActivity extends FragmentActivity implements OnMapReadyCallback {

    /** Variable de tipo {@link ServiceStop} **/
    private ServiceStop mServiceStop;

    /** Variable de tipo {@link GoogleMap} **/
    private GoogleMap mMap;

    /** Lista de tipo {@link Stop} */
    private List<Stop> mStop;

    /** String que contiene el id de la ruta */
    private String mIdRoute;

    /** String que contiene nombre de la ruta */
    private String mNameRuta;

    /** String que contiene la descripción de la ruta */
    private String mNameDescription;

    /** String que contiene la url de la ruta */
    private String mUrlsStop;

    /** String que contiene la url trasnformada */
    private static String mUrlChange;

    /** Tag para logs **/
    private static final String TAG_LOG = StopActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Inicializa el servicio
        mServiceStop = RestClient.getServiceStop();

        // Variable de tipo Intent
        Intent intent = getIntent();

        //Incializa la lista
        mStop = new ArrayList<Stop>();

        /** Guarda el id de la Ruta */
        mIdRoute = getIntent().getStringExtra("ID_ROUTE");

        /** Guarda el nombre de la Ruta */
        mNameRuta = getIntent().getStringExtra("NAME_ROUTE");

        /** Guarda el la descripción de la Ruta */
        mNameDescription = getIntent().getStringExtra("DESCRIPTION_ROUTE");

        /** Guarda el la descripción de la Ruta */
        mUrlsStop = getIntent().getStringExtra("URL_STOPS");

        //Se asigna la url trandformado al string
        mUrlChange = deleteString(mUrlsStop);

        //Invocacion del metodo que trae los datos del web service
        loadData();
    }

    /**
     * Método que realiza trandrorma la url ce las rutas
     * para ser usada en el servicio {@link ServiceStop}
     * @param mUrl
     * @return String
     */
    private String deleteString(String mUrl)
    {
        String mChagngeUrl = "";
        try
        {
            String mOriginaUrl = mUrl;
            mChagngeUrl = mOriginaUrl.substring(mOriginaUrl.indexOf(ConstantUtil.BASEURL)+
                    StringUtils.VALOR);
            mChagngeUrl.trim();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), StringUtils.NO_URL,
                    Toast.LENGTH_SHORT).show();
        }
        return mChagngeUrl;
    }

    /**
     * Metodo que carga la información del Web Service de las Rutas
     */
    private void loadData(){
        try
        {
            mServiceStop.getAnswers(mUrlChange).enqueue(new Callback<Stops>() {
                @Override
                public void onResponse(Call<Stops> call, Response<Stops> response) {
                    if(response.isSuccessful())
                    {
                        mStop = response.body().getStops();
                        if(mStop.size()>0){}
                        else
                        {
                            Toast.makeText(getApplicationContext(), StringUtils.NO_DATOS,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), StringUtils.NO_CONEXION,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Stops> call, Throwable t) {}
            });
        }
        catch (Exception es)
        {
            Toast.makeText(getApplicationContext(), StringUtils.NO_CONEXION,
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /***
     * Metodo autogenerado del OnMapReadyCallback
     * que muestra la ruta en el mapa
     **/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Inicialixa la variable mMap
        mMap = googleMap;

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                List<LatLng> locations = new ArrayList<>();
                for(Stop s :mStop)
                {
                    locations.add(new LatLng(s.getLat(),s.getLat()));
                }
                Log.d(TAG_LOG, "Url Stop 1 = " + locations);


                for(LatLng latLng : locations) {
                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.
                            fromResource(R.drawable.ic_school)).position(latLng).
                            title(mNameRuta).
                            snippet(latLng.latitude + " - " + latLng.latitude));
                }

                //LatLngBound will cover all your marker on Google Maps
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(locations.get(0)); //Taking Point A (First LatLng)
                builder.include(locations.get(locations.size() - 1)); //Taking Point B (Second LatLng)*/
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 13);
                mMap.moveCamera(cu);
                mMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID);
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
            }
        });
    }


    /** Metodo que devuelve a la actividad {@link RouteActivity} **/
    @Override
    public void onBackPressed()
    {
        Intent mIntent = new Intent(StopActivity.this, RouteActivity.class);
        startActivity(mIntent);
    }
}
