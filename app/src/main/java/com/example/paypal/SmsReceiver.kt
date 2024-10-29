package com.example.paypal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SmsReceiver : BroadcastReceiver() {
    var TAG = SmsReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"u received an sms 2")
    }
}