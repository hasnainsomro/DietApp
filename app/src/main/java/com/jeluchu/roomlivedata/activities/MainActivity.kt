package com.jeluchu.roomlivedata.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker

import androidx.appcompat.app.AppCompatActivity

import com.jeluchu.roomlivedata.R
import com.jeluchu.roomlivedata.alarms.AlarmNotificationReceiver

import java.util.Calendar

class MainActivity : AppCompatActivity() {
    internal var calender: Button? = null
    internal var hours: Int = 0
    internal var minss: Int = 0
    internal var picker: TimePickerDialog? = null
    internal var show: Button? = null
    internal var txtTime: TextView? = null

    /* access modifiers changed from: protected */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
     //   show = findViewById<View>(R.id.btn_show) as Button
        calender = findViewById<View>(R.id.calender) as Button
        txtTime = findViewById<View>(R.id.txtTime) as TextView
//        show!!.setOnClickListener {
//            this@MainActivity.startAlarm(this.hours, this.minss)
////            this@MainActivity.startAlarm(this.hours, this.minss + 2)
////            this@MainActivity.startAlarm(this.hours, this.minss + 4)
//
//        }
        calender!!.setOnClickListener {
            val cldr = Calendar.getInstance()
       val hour = cldr.get(Calendar.HOUR_OF_DAY)
            val minutes = cldr.get(Calendar.MINUTE)
            val mainActivity = this@MainActivity
            val timePickerDialog = TimePickerDialog(
                mainActivity,
                OnTimeSetListener { tp, sHour, sMinute ->
                    this@MainActivity.startAlarmnew(
                        sHour,
                        sMinute
                    )
                },
                hour,
                minutes,
                true
            )
            mainActivity.picker = timePickerDialog
            this@MainActivity.picker!!.show()
        }
    }

    /* access modifiers changed from: private */
    fun startAlarmnew(hour: Int, mins: Int) {
        this.hours = hour
        this.minss = mins
        val textView = this.txtTime
        val sb = StringBuilder()
        sb.append(hour)
        sb.append(" : ")
        sb.append(mins)
        textView!!.text = sb.toString()

        this@MainActivity.startAlarm(this.hours, this.minss)
        this@MainActivity.startAlarm(this.hours, this.minss+1)
       // this@MainActivity.startAlarm(this.hours, this.minss+1)
    }

    /* access modifiers changed from: private */
    fun startAlarm(hour: Int, mins: Int) {
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, mins)

        var intent = Intent(this, AlarmNotificationReceiver::class.java)
        var id = Utils.createID()

        val sb = StringBuilder()
        sb.append(hour)
        sb.append(" : ")
        sb.append(mins)
        intent.putExtra("date", sb.toString())
        intent.putExtra("notiId", id)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            intent,
            0
        )

        manager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            86400000,
            pendingIntent
        )
    }


}
