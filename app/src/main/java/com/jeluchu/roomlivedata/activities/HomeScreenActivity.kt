package com.jeluchu.roomlivedata.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeluchu.roomlivedata.R
import com.jeluchu.roomlivedata.alarms.AlarmNotificationReceiver

import com.jeluchu.roomlivedata.model.Notification
import com.jeluchu.roomlivedata.utils.Constants
import com.jeluchu.roomlivedata.utils.SharedPreferenceHelper
import com.jeluchu.roomlivedata.utils.StringSplit
import com.jeluchu.roomlivedata.viewmodels.WordViewModel
import kotlinx.android.synthetic.main.activity_home_screen.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeScreenActivity : AppCompatActivity() {
    private var mActive: Boolean = false

    private var mHandler: Handler? = null
    private var intentAlarm: Intent? = null

    var notificationList = ArrayList<Notification>()
    var manager: AlarmManager? = null
    private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    var sharedPreferenceHelper: SharedPreferenceHelper? = null

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferenceHelper = SharedPreferenceHelper.instance


        if (sharedPreferenceHelper!!.getBoolean(Constants.Theme)) {
            setTheme(R.style.darkTheme)

        } else {
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)



        wordViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(WordViewModel::class.java)

//        wordViewModel.allWords.observe(this, androidx.lifecycle.Observer { words ->
//
//
//            for (i in 0..words.size - 1) {
//                wordViewModel.insert(notificationList[i])
//                setAlarm(notificationList[i])
//                Log.e("HomeScreenActivity", "setAlarm: " + notificationList[i])
//            }
//
//            //  Log.e("HomeScreenActivity", "setAlarm: " + notificationList[words.size ])
//        })


//            for (i in 0..words.size - 1) {
//                if (words[i].alarmType.equals(alarmType)) {
//                    notificationList.add(words[i])
//                }
//            }
//            // setAlarm(notificationList)
//            adapter.setWords(notificationList)


        if (!sharedPreferenceHelper!!.getBoolean(Constants.defualtAlarmFirstTime)) {
            manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            intentAlarm = Intent(this, AlarmNotificationReceiver::class.java)
            freshAirFun()
            goodNightSleep()
            drinkWater()
            physicalActivity()
            eatHealthyFood()
            gooutinSun()
            blief()
            tech()
            control()
            sharedPreferenceHelper!!.setBoolean(Constants.defualtAlarmFirstTime, true)
        }



        getSupportActionBar()!!.hide()
        mHandler = Handler()

        startClock()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.getMenu().getItem(0).setCheckable(false)

        id_setting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()

        }

        id_selectdiet.setOnClickListener {
            val intent = Intent(this, DietFactorActivity::class.java)
            startActivity(intent)

        }
        id_notification.setOnClickListener {
            val intent = Intent(this, RemindersActivity::class.java)
            startActivity(intent)

        }
        id_stopwatch.setOnClickListener {
            val intent = Intent(this, ChewDataActivity::class.java)
            startActivity(intent)

        }


    }

//    private fun setAlarm(notificationList: List<Notification>) {
//        Log.e("AddNewReminderActivity", "setAlarm: ")
//
//        for (i in notificationList.indices) {
//
//
//            var intent = Intent(this, AlarmReciever::class.java)
//            intent.putExtra("Title", notificationList[i].word)
//            intent.putExtra("Text", "new notification" + i)
//            intent.putExtra("vibration", notificationList[i].vibration)
//            intent.putExtra("sound", notificationList[i].sound)
//            intent.putExtra("id", notificationList[i].id)
//            intent.putExtra("monday", notificationList[i].monday)
//            intent.putExtra("tuesday", notificationList[i].tuesday)
//            intent.putExtra("wednesday", notificationList[i].wednesday)
//            intent.putExtra("thursday", notificationList[i].thursday)
//            intent.putExtra("friday", notificationList[i].friday)
//            intent.putExtra("saturday", notificationList[i].saturday)
//            intent.putExtra("sunday", notificationList[i].sunday)
//            var res = notificationList[i].word.split(":")
//
//            val calendar: Calendar = Calendar.getInstance().apply {
//                timeInMillis = System.currentTimeMillis()
//                set(Calendar.HOUR_OF_DAY, res[0].toInt())
//                set(Calendar.MINUTE, res[1].toInt())
//            }
//
//
//
//            pendingintentArray.add(
//                PendingIntent.getBroadcast(
//                    this,
//                    notificationList[i].id,
//                    intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//                )
//            )
////            manager?.setRepeating(
////                AlarmManager.RTC_WAKEUP,
////                calendar.timeInMillis,
////                AlarmManager.INTERVAL_DAY,
////                pendingintentArray[i]
////            )
//
//
//            manager?.setExact(
//                AlarmManager.RTC_WAKEUP,
//                calendar.timeInMillis,
//                pendingintentArray[i]
//
//            )
//            Log.e("AddNewReminderActivity", "setAlarm: " + pendingintentArray[i])
//
//        }
//
//    }

    private fun startClock() {
        mActive = true
        mHandler!!.post(mRunnableclock)
    }

    var mRunnableclock: Runnable = object : Runnable {
        override fun run() {
            if (mActive) {
                time.text = getTime()
                mHandler!!.postDelayed(this, 1000)
            }
        }

        private fun getTime(): String {
            return sdf.format(Date(System.currentTimeMillis()))
        }
    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {

            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.getItemId()) {
                    R.id.action_item1 -> {
                        startActivityNineFactor()

                        return true
                    }
                    R.id.action_item2 -> {
                        startActivityChew()

                        return true
                    }
                    R.id.action_item3 -> {
                        startActivityDietFactor()
                        return true
                    }
                    R.id.action_item4 -> {
                        startActivitySupport()
                        return true
                    }
                    R.id.action_item5 -> {
                        startTodayAlarms()
                        return true
                    }
                }
                return false
            }
        }

    private fun startTodayAlarms() {
        val intent = Intent(this, TodayAlarmActivity::class.java)
        startActivity(intent)
        navigation.getMenu().getItem(0).setCheckable(true)
    }

    private fun startActivitySearch() {
        return
    }

    private fun startActivitySupport() {
        return
    }

    private fun startActivityChew() {
        val intent = Intent(this, ChewDataActivity::class.java)
        startActivity(intent)
    }

    private fun startActivityNineFactor() {
        val intent = Intent(this, RemindersActivity::class.java)
        startActivity(intent)
    }

    private fun startActivityDietFactor() {
        val intent = Intent(this, DietFactorActivity::class.java)
        startActivity(intent)
    }


    private fun control() {
        val notificationModela = Notification(
            0, 17,
            "23:10",
            status = false,
            vibration = true,
            sound = true,
            alarmType = Constants.Control,
            monday = true,
            tuesday = true,
            wednesday = true,
            thursday = true,
            friday = true,
            saturday = true,
            sunday = true
        )


        // wordViewModel.insert(notificationModela)
        notificationList.add(notificationModela)
        // setAlarm(notificationModela)

        val notificationModelb =
            Notification(
                0,
                18,
                "23:20",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.Control,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        notificationList.add(notificationModelb)


        for (i in 0 until notificationList.size) {
            wordViewModel.insert(notificationList[i])
            setAlarm(notificationList[i])
            Log.e("HomeScreenActivity", "setAlarm: " + notificationList[i])
        }
    }

    private fun tech() {
        val notificationModela =
            Notification(
                0,
                16,
                "07:00",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.Tech,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        notificationList.add(notificationModela)
    }


    private fun blief() {
        val notificationModela =
            Notification(
                0,
                15,
                "22:00",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.Blief,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModela)
        //setAlarm(notificationModela)
        notificationList.add(notificationModela)
    }

    private fun gooutinSun() {
        val notificationModela =
            Notification(
                0,
                14,
                "12:45",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.GoOutSun,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModela)
        // setAlarm(notificationModela)
        notificationList.add(notificationModela)

    }

    private fun eatHealthyFood() {
        val notificationModela =
            Notification(
                0,
                11,
                "10:45",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.EatHealthy,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModela)
        //  setAlarm(notificationModela)
        notificationList.add(notificationModela)
        val notificationModelb =
            Notification(
                0,
                12,
                "16:45",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.EatHealthy,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModelb)
//        setAlarm(notificationModelb)
        notificationList.add(notificationModelb)

        val notificationModelc =
            Notification(
                0,
                13,
                "20:45",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.EatHealthy,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModelc)
        // setAlarm(notificationModelc)
        notificationList.add(notificationModelc)

    }

    private fun physicalActivity() {
        val notificationModela =
            Notification(
                0,
                10,
                "18:30",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Excercise,
                monday = false,
                tuesday = true,
                wednesday = false,
                thursday = true,
                friday = false,
                saturday = false,
                sunday = true
            )

        // wordViewModel.insert(notificationModela)
        //     setAlarm(notificationModela)
        notificationList.add(notificationModela)
    }

    private fun drinkWater() {
        val notificationModel =
            Notification(
                0,
                2,
                "07:00",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModel)
        // setAlarm(notificationModel)
        notificationList.add(notificationModel)

        val notificationModelc =
            Notification(
                0,
                3,
                "09:50",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModelc)
        //setAlarm(notificationModelc)
        notificationList.add(notificationModelc)

        val notificationModeld =
            Notification(
                0,
                4,
                "11:00",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        // wordViewModel.insert(notificationModeld)
        //   setAlarm(notificationModeld)
        notificationList.add(notificationModeld)

        val notificationModelh =
            Notification(
                0,
                5,
                "12:50",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        // wordViewModel.insert(notificationModelh)
        notificationList.add(notificationModelh)
        //  setAlarm(notificationModelh)


        val notificationModeli =
            Notification(
                0,
                6,
                "14:00",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        // wordViewModel.insert(notificationModeli)
        //  setAlarm(notificationModeli)
        notificationList.add(notificationModeli)

        val notificationModell =
            Notification(
                0,
                7,
                "16:50",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        // wordViewModel.insert(notificationModell)
        // setAlarm(notificationModell)
        notificationList.add(notificationModell)

        //   setAlarm(notificationModell)
        val notificationModelm =
            Notification(
                0,
                8,
                "18:00",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        // wordViewModel.insert(notificationModelm)
        // setAlarm(notificationModelm)
        notificationList.add(notificationModelm)

        val notificationModelp =
            Notification(
                0,
                9,
                "20:50",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Water,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        // wordViewModel.insert(notificationModelp)
        //   setAlarm(notificationModelp)
        notificationList.add(notificationModelp)
    }

    private fun goodNightSleep() {
        val notificationModel =
            Notification(
                0,
                1,
                "13:50",
                status = false,
                vibration = false,
                sound = false,
                alarmType = Constants.Sleep,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )
        notificationList.add(notificationModel)
    }

    private fun freshAirFun() {
        val notificationModel =
            Notification(
                0,
                0,
                "17:15",
                status = false,
                vibration = true,
                sound = true,
                alarmType = Constants.Breath,
                monday = true,
                tuesday = true,
                wednesday = true,
                thursday = true,
                friday = true,
                saturday = true,
                sunday = true
            )

        notificationList.add(notificationModel)
    }


    private fun setAlarm(notification: Notification) {
        /* Retrieve a PendingIntent that will perform a broadcast */
        Toast.makeText(this, "Alarm has been set!", Toast.LENGTH_LONG).show()
        startAlarm(
            StringSplit.getIntAt0(notification.time),
            StringSplit.getIntAt1(notification.time),
            notification.notiId
        )
    }


    /* access modifiers changed from: private */
    fun startAlarm(hour: Int, minutes: Int, id: Int) {
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minutes)

        val intent = Intent(this, AlarmNotificationReceiver::class.java)

        val sb = StringBuilder()
        sb.append(hour)
        sb.append(" : ")
        sb.append(minutes)

        intent.putExtra("date", sb.toString())
        intent.putExtra("notiId", id)

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            intent,
            0
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            manager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }
}
