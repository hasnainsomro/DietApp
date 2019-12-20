import android.content.Context
import android.content.Intent
import com.jeluchu.roomlivedata.alarms.AlarmNotificationReceiver
import com.jeluchu.roomlivedata.model.Notification
import com.jeluchu.roomlivedata.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun millitohourmin(timeOfAlarm: Long): String {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeOfAlarm


        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        return (hour.toString() + " : " + minutes.toString())
    }

    fun setAlarmNew(mainActivity: Context, notification: Notification, i: Int) {


        //Setting intent to class where Alarm broadcast message will be handled

        val broadcastIntent = Intent(mainActivity, AlarmNotificationReceiver::class.java)
        broadcastIntent.putExtra("id", i)
        broadcastIntent.putExtra(Constants.AlarmType, notification.alarmType)
        broadcastIntent.putExtra("date", notification.time)
        broadcastIntent.putExtra("monday", notification.monday)
        broadcastIntent.putExtra("tuesday", notification.tuesday)
        broadcastIntent.putExtra("wednesday", notification.wednesday)
        broadcastIntent.putExtra("thursday", notification.thursday)
        broadcastIntent.putExtra("friday", notification.friday)
        broadcastIntent.putExtra("saturday", notification.saturday)
        broadcastIntent.putExtra("sunday", notification.sunday)
        broadcastIntent.putExtra("vibration", notification.vibration)
        broadcastIntent.putExtra("sound", notification.sound)

    }

    fun createID(): Int {
        val now = Date()
        return Integer.parseInt(SimpleDateFormat("ddHHmmss", Locale.US).format(now))
    }



}

//fun cancelAlarm(mainActivity: Context, notification: Notification) {
//
//
//    //Setting intent to class where Alarm broadcast message will be handled
//
//    val broadcastIntent = Intent(mainActivity, AlarmReceiver::class.java)
//    broadcastIntent.putExtra("id", notification.notiId)
//    broadcastIntent.putExtra("date", millitohourmin(notification.word))
//    broadcastIntent.putExtra("monday", notification.monday)
//    broadcastIntent.putExtra("tuesday", notification.tuesday)
//    broadcastIntent.putExtra("wednesday", notification.wednesday)
//    broadcastIntent.putExtra("thursday", notification.thursday)
//    broadcastIntent.putExtra("friday", notification.friday)
//    broadcastIntent.putExtra("saturday", notification.saturday)
//    broadcastIntent.putExtra("sunday", notification.sunday)
//    broadcastIntent.putExtra("vibration", notification.vibration)
//    broadcastIntent.putExtra("sound", notification.sound)
//
//    // broadcastIntent.putExtra("date", hourOfDayg.toString() + ":" + minuteOfHourg.toString())
//
//    //Setting alarm pending intent
//    alarmIntentRTC = PendingIntent.getBroadcast(
//        mainActivity,
//        notification.id,
//        broadcastIntent,
//        PendingIntent.FLAG_UPDATE_CURRENT
//    )
//
//    //getting instance of AlarmManager service
//    alarmManagerRTC = mainActivity.getSystemService(ALARM_SERVICE) as AlarmManager
//
//
//    alarmManagerRTC!!.cancel(alarmIntentRTC)
//
//}

