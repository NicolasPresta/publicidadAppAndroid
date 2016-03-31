package com.example.presta.publicidadexample.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.presta.publicidadexample.common.AlarmGPSStart;

/**
 * Created by Presta on 27/03/2016.
 */
public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmGPSStart.setAlarm(context);
    }
}
