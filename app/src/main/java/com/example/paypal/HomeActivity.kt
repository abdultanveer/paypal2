package com.example.paypal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    lateinit var  homeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) //inflating
        homeTextView = findViewById(R.id.tvHome)
        //get the intent which started this activity, get extras from intent and show it in a textview
        var mydata = intent.getStringExtra("co")
        homeTextView.setText(mydata)
    }
}