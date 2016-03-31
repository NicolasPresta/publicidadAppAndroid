package com.example.presta.publicidadexample.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.presta.publicidadexample.receivers.AlarmGetGPSReceiver;
import com.example.presta.publicidadexample.receivers.AlarmSincGPSReceiver;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Presta on 27/03/2016.
 */
// Esto es el seteo de la alarma que despierta al sensor de GPS. cuando suena la alarma el Receiver se levanta y ejecuta su magia.
// Nota: Lo puse como un common pq se invoca tanto desde el main como desde el BootUpReceiver. Osea, la alarma se setea cuando se ejecuta la app,
// Pero tambien cuando se inicia el SO.
public class AlarmGPSStart {

    public static void setAlarm(Context context) {

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        // Al prender el celular, se prepara la alarma que va a monitorear el GPS
        Intent alarmIntentGetGps = new Intent(context, AlarmGetGPSReceiver.class);
        PendingIntent pendingIntentGetGps = PendingIntent.getBroadcast(context, 0, alarmIntentGetGps, 0);
        int interval = 9 * 60 * 60 * 1000; // Cada 9hs
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntentGetGps);

        // A la madrugada y cada 2 dias, se activa la alarma para sincronizar el GPS
        Intent alarmIntentSincGps = new Intent(context, AlarmSincGPSReceiver.class);
        PendingIntent pendingIntentSincGps = PendingIntent.getBroadcast(context, 0, alarmIntentSincGps, 0);
        int intervalGetGps = 48 * 60 * 60 * 1000; // Cada 48hs
        Calendar calendar = Calendar.getInstance();
        // La sincronización se va a hacer aleatoriamente entre la 1 y las 6 de la mañana.
        Random r = new Random();

        int hora = r.nextInt(7 - 1) + 1;
        int minuto = r.nextInt(60 - 1) + 1;
        int segundo = r.nextInt(60 - 1) + 1;

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hora, minuto, segundo);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalGetGps, pendingIntentSincGps);

    }
}
