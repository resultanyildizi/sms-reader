package com.example.smsreader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast


class SmsReceiver : BroadcastReceiver() {


    private val TAG: String? = "SMS RECEIVER"

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            val i = Intent(context, SmsService::class.java)
            context.startService(i);
        } else {
            Log.d(TAG, "onReceive: ${intent.action}")
            readSmsContent(context, intent)
        }

    }

    private fun readSmsContent(context: Context, intent: Intent) {
        val myBundle = intent.extras
        var messages: Array<SmsMessage?>? = null
        var smsMessage: String? = ""
        var smsSender: String? = ""

        if (myBundle != null) {
            val pdus = myBundle["pdus"] as Array<Any>?
            messages = arrayOfNulls(pdus!!.size)
            for (i in messages.indices) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val format = myBundle.getString("format")
                    messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray, format)
                } else {
                    messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                }
                smsSender = messages[i]!!.originatingAddress
                smsMessage = messages[i]!!.messageBody
            }
            Toast.makeText(context, "Sender: $smsSender \n message: $smsMessage", Toast.LENGTH_SHORT).show()
        }
    }
}