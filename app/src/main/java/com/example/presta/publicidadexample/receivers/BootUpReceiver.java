package com.example.presta.publicidadexample.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.presta.publicidadexample.common.helpers.AlarmGPSStart;

/**
 * Created by Presta on 27/03/2016.
 */
public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmGPSStart.setAlarm(context);
    }
}
