package com.example.smsreader

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class SmsService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return null!!;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")
        Toast.makeText(this, "Sms service started $action", Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }


}