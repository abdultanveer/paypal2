package com.example.paypal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showSnackBar(view: View) {
        var data = Uri.parse("tel:12345678")
       var dialIntent = Intent(Intent.ACTION_DIAL,data) //implicit intent
        startActivity(dialIntent)
    }

    private fun startHome() {
        var intention = Intent(this, HomeActivity::class.java) //explicit intent
        intention.putExtra("co", "pypl")
        startActivity(intention)
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
}