package com.jeluchu.roomlivedata.alarms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        startAlarm(true, true);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return 2;
    }

    private void startAlarm(boolean isNotification, boolean isRepeat) {
//        AlarmManager manager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(11, 18);
//        calendar.set(12, 45);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmNotificationReceiver.class), 0);
//        if (!isRepeat) {
//            manager.set(0, SystemClock.elapsedRealtime() + 3000, pendingIntent);
//            return;
//        }
//        manager.setRepeating(0, calendar.getTimeInMillis(), 86400000, pendingIntent);
    }
}
