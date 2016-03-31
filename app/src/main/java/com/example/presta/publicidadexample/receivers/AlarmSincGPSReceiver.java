package com.example.presta.publicidadexample.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.presta.publicidadexample.dataAccess.dao.DaoSessionAccesor;
import com.example.presta.publicidadexample.dataAccess.model.GpsData;
import com.example.presta.publicidadexample.dataAccess.model.GpsDataDao;
import com.example.presta.publicidadexample.rest.ApiConstants;
import com.example.presta.publicidadexample.rest.post.OnPostCompleted;
import com.example.presta.publicidadexample.rest.post.PostRequestTask;
import com.example.presta.publicidadexample.services.GPSTracker;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Presta on 30/03/2016.
 */
public class AlarmSincGPSReceiver extends BroadcastReceiver
        implements OnPostCompleted  {

    GpsDataDao gpsDataDao;

    @Override
    public void onReceive(Context context, Intent intent) {
        // For our recurring task, we'll just display a message

        gpsDataDao = DaoSessionAccesor.GetDaoSession(context).getGpsDataDao();

        // La sincronización de los datos del GPS lo hacemos en un hilo aparte para no sobrecargar el hilo de la UI
        List gpsDatas = gpsDataDao.loadAll();
        if (gpsDatas.size() > 0) {
            Log.i("GPS", "hay algun GPS data");

            Map<String, String> map = new HashMap<String, String>();
            map.put("Ubicaciones", ObtenerJsonGPSDatas(gpsDatas.toArray()));

            String param = PostRequestTask.createQueryStringForParameters(map);

            new PostRequestTask(this).execute(ApiConstants.POST_METHOD_GPSDATA, param);
        }
    }

    @Override
    public void onPostCompleted(String metodo, Integer result) {

        // Si la ejecución del post del gpsData fue exitosa, borramos los registros sincronizados y nunca mas nos preocuparemos por ellos :)
        if (metodo == ApiConstants.POST_METHOD_GPSDATA)
            if (result == HttpURLConnection.HTTP_OK) {
                Log.i("POST", "los gpsData se sincronizaron ok");
                gpsDataDao.deleteAll();
            }
    }

    private String ObtenerJsonGPSDatas(Object[] gpsDatas) {

        String ubicaciones = "{ ubicaciones: [";

        try {

            for (Object gpsData : gpsDatas) {

                GpsData data = (GpsData) gpsData;

                String ubicacion = "{lat:" + data.getLat().toString() + ",";
                ubicacion = ubicacion + "lon:" + data.getLon().toString() + ",";
                ubicacion = ubicacion + "fecha:\"" + data.getFecha().toString() + "\",";
                ubicacion = ubicacion + "modo:\"" + data.getModo() + "\"},";
                ubicaciones = ubicaciones + ubicacion;
            }
        } catch (Exception e) {
            Log.i("READ_Exception", "Exception:" + e);
        }

        ubicaciones = ubicaciones.substring(0, ubicaciones.length() - 1);
        ubicaciones = ubicaciones + "]}";

        return ubicaciones;
    }

}