package com.jeluchu.roomlivedata.alarms;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class MyAliveReciver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        Toast.makeText(context, "inside receiver", Toast.LENGTH_SHORT).show();
    }
}