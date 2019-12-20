package com.jeluchu.roomlivedata.activities

import android.content.Intent
import android.net.Uri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jeluchu.roomlivedata.R
import com.jeluchu.roomlivedata.utils.Constants
import com.jeluchu.roomlivedata.utils.SharedPreferenceHelper

import kotlinx.android.synthetic.main.activity_diet.*


class DietActivity : AppCompatActivity() {
    var dietno: String? = null

    var sharedPreferenceHelper: SharedPreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferenceHelper = SharedPreferenceHelper.instance


        if (sharedPreferenceHelper!!.getBoolean(Constants.Theme)) {
            setTheme(R.style.darkTheme)

        } else {
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        getSupportActionBar()!!.hide()
        setContentView(R.layout.activity_diet)
        dietno = intent.getStringExtra(Constants.Dietno)
        setTitle("Diet " + dietno)

        id_calculate.setOnClickListener { onLinkClick1() }
        id_valiktin.setOnClickListener { onLinkClick2() }
        id_inform.setOnClickListener { onLinkClick3() }
        id_educate.setOnClickListener { onLinkClick4() }
        id_fight.setOnClickListener { onLinkClick5() }
        id_certify.setOnClickListener { onLinkClick6() }
        id_affiliate.setOnClickListener { onLinkClick7() }
    }

    private fun onLinkClick7() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE7")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick6() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE6")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick5() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE5")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick4() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE4")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick3() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE3")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick2() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE2")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }

    private fun onLinkClick1() {
        val uris = Uri.parse("https://nutrieveryday.com/MODULE1")
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        this.startActivity(intents)
    }


}
