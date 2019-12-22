package com.jeluchu.roomlivedata.alarms

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.jeluchu.roomlivedata.R
import com.jeluchu.roomlivedata.activities.MainActivity
import java.util.*


class AlarmNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        when (day) {
            Calendar.SATURDAY -> {
                if (intent.getBooleanExtra("saturday", false)) {
                    showNotificationDay(intent, context)
                }
            }

            Calendar.SUNDAY -> {
                if (intent.getBooleanExtra("sunday", false)) {
                    //  sendNotification2(context);
                    showNotificationDay(intent, context)
                }
            }

            Calendar.MONDAY -> {
                if (intent.getBooleanExtra("monday", false)) {
                    showNotificationDay(intent, context)
                }
            }

            Calendar.TUESDAY -> {
                if (intent.getBooleanExtra("tuesday", false)) {
                    showNotificationDay(intent, context)
                }
            }

            Calendar.WEDNESDAY -> {
                if (intent.getBooleanExtra("wednesday", false)) {
                    showNotificationDay(intent, context)
                }
            }

            Calendar.THURSDAY -> {
                if (intent.getBooleanExtra("thursday", false)) {
                    showNotificationDay(intent, context)
                }
            }

            Calendar.FRIDAY -> {
                if (intent.getBooleanExtra("friday", false)) {
                    showNotificationDay(intent, context)
                }
            }
        }

        showNotificationDay(intent, context)
    }

    private fun showNotificationDay(intent: Intent, context: Context) {
        val myIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            intent.getIntExtra("notiId", 0),
            myIntent,
            FLAG_ONE_SHOT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Alarm"
            val descriptionText = "Alarm details"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("AlarmId", name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val mBuilder = NotificationCompat.Builder(context, "AlarmId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm")
            .setContentText(intent.getStringExtra("date"))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (intent.getBooleanExtra("vibration", false))
            mBuilder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        if (intent.getBooleanExtra("sound", false))
            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            intent.getIntExtra("notiId", 0), mBuilder.build()
        )

        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val nextPendingIntent = PendingIntent.getBroadcast(
            context,
            intent.getIntExtra("notiId", 0),
            intent,
            0
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                86400000,
                pendingIntent
            )
        } else {
            manager.setExact(
                AlarmManager.RTC_WAKEUP,
                86400000,
                pendingIntent
            )
        }
    }
}
