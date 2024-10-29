package com.example.paypal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log


class SmsReceiver : BroadcastReceiver() {
    var TAG = SmsReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
                val bundle = intent.extras
                if (bundle != null) {
                    val pdus = bundle["pdus"] as Array<Any>?
                    val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus!!.size)
                    for (i in pdus!!.indices) {
                        messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                        Log.e("Message Content : ", " == " + messages[i]?.getMessageBody())
                        Log.e(
                            "Message Content Body : ",
                            " == " + messages[i]?.getDisplayMessageBody()
                        )
                        Log.e("Message recieved From", " == " + messages[0]?.getOriginatingAddress())
                    }
                    /*if (messages.length > -1) {
                Log.e("Message recieved: "," == "+ messages[0].getMessageBody());
                Log.e("Message recieved From"," == "+ messages[0].getOriginatingAddress());
            }*/
                }
            }
        }


}