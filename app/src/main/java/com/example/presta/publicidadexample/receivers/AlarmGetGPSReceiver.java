package com.example.presta.publicidadexample.receivers;

/**
 * Created by Presta on 27/03/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.presta.publicidadexample.dataAccess.dao.DaoSessionAccesor;
import com.example.presta.publicidadexample.dataAccess.model.GpsData;
import com.example.presta.publicidadexample.dataAccess.model.GpsDataDao;
import com.example.presta.publicidadexample.services.GPSTracker;

import java.util.Calendar;

public class AlarmGetGPSReceiver extends BroadcastReceiver {

    GPSTracker gps;
    GpsDataDao gpsDataDao;

    @Override
    public void onReceive(Context context, Intent intent) {
        // For our recurring task, we'll just display a message


        // create class object
        gps = new GPSTracker(context);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            try {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                String provider = gps.getProvider();
                Calendar c = Calendar.getInstance();

                // Grabamos el registro
                gpsDataDao = DaoSessionAccesor.GetDaoSession(context).getGpsDataDao();
                GpsData dataGPS = new GpsData();
                dataGPS.setLat(latitude);
                dataGPS.setLon(longitude);
                dataGPS.setFecha(c.getTime());
                dataGPS.setModo(provider);
                gpsDataDao.insert(dataGPS);
            }
            catch (Exception e)
            {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
    }

}
