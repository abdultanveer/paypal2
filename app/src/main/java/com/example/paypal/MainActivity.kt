package com.example.paypal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var  textView: TextView
    lateinit var nameEditText: EditText

    var TAG = MainActivity::class.java.simpleName

        //"MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       textView = findViewById(R.id.textView)
        nameEditText = findViewById(R.id.etName)
        Log.i(TAG,"oncreate-egg")
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG,"onstart-hatched-visible")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onpause-sleep-store data")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG,"onresume-wakeup-restore data")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"onstop-hibernate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"ondestroy-release resources")
    }



    fun showSnackBar(view: View) {
        startHome()
    }

    private fun startDialer() {
        var data = Uri.parse("tel:12345678")
        var dialIntent = Intent(Intent.ACTION_DIAL, data) //implicit intent
        startActivity(dialIntent)
    }

    private fun startHome() {
        var intention = Intent(this, HomeActivity::class.java) //explicit intent
        intention.putExtra("co", "pypl")
        startActivityForResult(intention,123)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, dataIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, dataIntent)
        if(resultCode == RESULT_OK){
            var phno = dataIntent?.getStringExtra("phno")
            textView.setText(phno)
        }
    }


    private fun showSnack() {
        //int a = 10
        var a = 10
        var view = findViewById<ConstraintLayout>(R.id.main)
        var mysnack = Snackbar.make(this, view, "undo delete", Snackbar.LENGTH_LONG)
        mysnack.setAction("UNDO", {})
        mysnack.show()
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
       // if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        //}
    }

    fun setAlarm(view: View) {
        createAlarm("b2pypl",12,19)
    }

    fun notifyUser(view: View) {
        createNotificationChannel()
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("roshita")
            .setContentText("raghaveda")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                com.google.android.material.R.drawable.abc_btn_radio_to_on_mtrl_000, "call back",
                pendingIntent)
        var noObj = builder.build()
        //as = typecasting
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(777,noObj)
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ride related"
                //getString(R.string.channel_name)
            val descriptionText = "when ur ride arrives"
                //getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun startMusic(view: View) {
        var intent  = Intent(this,MusicService::class.java)
       intent.putExtra("co","paypl")
        startService(intent)
    }
    fun stopMusic(view: View) {
        var intent  = Intent(this,MusicService::class.java)
        stopService(intent)
    }

    fun handleSubmit(view: View) {
        var typed = nameEditText.text.toString()
        textView.setText(typed)
    }
}