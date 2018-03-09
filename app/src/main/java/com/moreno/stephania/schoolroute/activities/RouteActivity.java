package com.moreno.stephania.schoolroute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.moreno.stephania.schoolroute.R;
import com.moreno.stephania.schoolroute.adapters.RouteAdapter;
import com.moreno.stephania.schoolroute.models.Buses;
import com.moreno.stephania.schoolroute.models.SchoolBus;
import com.moreno.stephania.schoolroute.rest.RestClient;
import com.moreno.stephania.schoolroute.rest.interfaces.ServiceRoute;
import com.moreno.stephania.schoolroute.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Actividad que lista las Rutas programadas
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno.</a>
 */
public class RouteActivity extends AppCompatActivity {

    /**
     * Variable de tipo ServiceRoute
     */
    private ServiceRoute mService;
    /**
     * Swipe Refresh
     */
    private SwipeRefreshLayout mContainerSwipeRefreshLayout;

    /**
     * ListView que muestra la lista de Buese
     */
    private ListView mBusesLv;

    /**
     * Variable de tipo {@link com.moreno.stephania.schoolroute.adapters.RouteAdapter}
     */
    private RouteAdapter mAdapter ;

    /**
     * List de tipo {@link SchoolBus}
     */
    private List<SchoolBus> mSchoolBus;

    /**
     * Variable de tipo {@link SchoolBus}
     */
    private SchoolBus mStops;

    /** Tag para logs **/
    private static final String TAG_LOG = RouteActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        // Inicializacion de la variable
        mService = RestClient.getServiceRoute();

        // Inicialización de control SwipeRefreshLayout
        mContainerSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.container_srl);

        // Inicializaciópn de la lista
        mBusesLv = (ListView)findViewById(R.id.route_lv);

        mSchoolBus = new ArrayList<SchoolBus>();

        //Crea objeto de tipo Items
        mStops = new SchoolBus();

        //Invocacion del metodo que trae los datos del web service
        loadData();

        // Evento para actualizar la lista
        mContainerSwipeRefreshLayout.setOnRefreshListener(new
          SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
                  //Invocacion del metodo
                  loadData();
              }
          });

        // Configure the refreshing colors
        mContainerSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Onclick listener de la lista
        mBusesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentRoute = new Intent(RouteActivity.this, StopActivity.class);
                mStops = mSchoolBus.get(i);
                SchoolBus mSchoolBus = (SchoolBus) mBusesLv.getItemAtPosition(i);
                intentRoute.putExtra("ID_ROUTE", mSchoolBus.getId().toString());
                intentRoute.putExtra("NAME_ROUTE", mSchoolBus.getName().toString());
                intentRoute.putExtra("DESCRIPTION_ROUTE", mSchoolBus.getDescription().toString());
                intentRoute.putExtra("URL_STOPS", mSchoolBus.getStopsUrl());

                ColorDrawable[] mColorPrimary = {
                        new ColorDrawable(Color.parseColor("#e1f2f7")),
                        new ColorDrawable(Color.parseColor("#b6e2eb"))
                };
                ColorDrawable[] mColorSecond= {
                        new ColorDrawable(Color.parseColor("#b6e2eb")),
                        new ColorDrawable(Color.parseColor("#e1f2f7"))
                };
                TransitionDrawable mTrasnition = new TransitionDrawable(mColorSecond);
                view.setBackground(mTrasnition);
                mTrasnition.startTransition(100);

                startActivity(intentRoute);
            }
        });
    }

    /**
     * Metodo que carga la informacipon del web service
     */
    private void loadData(){
        try
        {
            mService.getRoute().enqueue(new Callback<Buses>() {
                @Override
                public void onResponse(Call<Buses> call, Response<Buses> response) {
                    if(response.isSuccessful())
                    {
                        mSchoolBus = response.body().getSchoolBuses();
                        Log.d(TAG_LOG, "Items = " + mSchoolBus.size());
                        mAdapter = new RouteAdapter(RouteActivity.this, mSchoolBus);
                        mBusesLv.setAdapter(mAdapter);
                        mContainerSwipeRefreshLayout.setRefreshing(false);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), StringUtils.NO_CONEXION,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Buses> call, Throwable t) {}
            });
        }catch (Exception e)
        {
           Toast.makeText(getApplicationContext(),
                   StringUtils.NO_CONEXION, Toast.LENGTH_SHORT).show();
        }
    }

    /** Metodo que cierra la aplicacion**/
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
