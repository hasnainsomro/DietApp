package com.jeluchu.roomlivedata.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyService.class));
        Toast.makeText(context, "Booting Completed", 1).show();
    }
}
