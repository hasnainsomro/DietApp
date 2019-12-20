package com.jeluchu.roomlivedata.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyNewIntentService extends IntentService {


    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
