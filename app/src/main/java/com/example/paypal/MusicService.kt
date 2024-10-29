package com.example.paypal

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.util.LogWriter

class MusicService : Service() {
var TAG = MusicService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service-created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        var data = intent?.getStringExtra("co")
        //context = history
        var winamp = MediaPlayer.create(this,R.raw.music)
        winamp.start()
        Log.d(TAG,"onstart--"+data)

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG,"service destroyed")
    }
}