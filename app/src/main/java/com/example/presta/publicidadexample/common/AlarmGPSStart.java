package com.example.presta.publicidadexample.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.presta.publicidadexample.receivers.AlarmGPSReceiver;

/**
 * Created by Presta on 27/03/2016.
 */
// Esto es el seteo de la alarma que despierta al sensor de GPS. cuando suena la alarma el Receiver se levanta y ejecuta su magia.
    // Nota: Lo puse como un common pq se invoca tanto desde el main como desde el BootUpReceiver. Osea, la alarma se setea cuando se ejecuta la app,
    // Pero tambien cuando se inicia el SO.
public class AlarmGPSStart {

    public static void setAlarm(Context context) {

        // Al prender el celular, se prepara la alarma que va a monitorear el GPS
        Intent alarmIntent = new Intent(context, AlarmGPSReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        int interval = 32400000; // Cada 9hs (9*60*60*1000)
        // int interval = 15*1000; // Cada 10min

        // TODO: verificar si usar RTC_WAKEUP u otro
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

       // Toast.makeText(context, "Alarma seteada", Toast.LENGTH_SHORT).show();

    }
}
