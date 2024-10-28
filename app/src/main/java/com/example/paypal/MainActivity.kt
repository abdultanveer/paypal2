package com.example.paypal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var  textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       textView = findViewById(R.id.textView)
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
}