package com.example.paypal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    lateinit var  homeTextView: TextView
    lateinit var contactEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) //inflating
        homeTextView = findViewById(R.id.tvHome)
        contactEditText = findViewById(R.id.etContact) //taking handle
        //get the intent which started this activity, get extras from intent and show it in a textview
        var mydata = intent.getStringExtra("co")
        homeTextView.setText(mydata)
    }

    fun sendContact(view: View) {
        //get the phno from the edittext
        var phnno = contactEditText.text.toString()
        //put phno in an intet
        var intentReturned = Intent()
        intentReturned.putExtra("phno",phnno)
        //send it to parent/mainactivity
        setResult(RESULT_OK,intentReturned)//
        //close this activity
        finish()
    }
}