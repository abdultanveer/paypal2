package com.example.paypal

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel

class DataViewModel:ViewModel() {
    var count =0
    lateinit var timer: CountDownTimer
    var _seconds:Int =  0    //_ means its a mutable variable

   fun incrementCountvar(){
       count++
   }

    fun startTimer():Unit{
        timer = object :CountDownTimer(10_000,1_000){
            override fun onTick(millisUntilFinished: Long) {
                Log.i(TAG,"time remaining --"+millisUntilFinished)
                _seconds = millisUntilFinished.toInt()
            }
            override fun onFinish() {
                Log.i(TAG,"timer finished")
            }
        }.start()
    }
    companion object{
        var TAG = DataViewModel::class.java.simpleName
    }

}