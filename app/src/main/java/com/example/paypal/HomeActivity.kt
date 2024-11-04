package com.example.paypal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paypal.databinding.ActivityHomeBinding
import com.example.paypal.network.MarsApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
   /* lateinit var  homeTextView: TextView
    lateinit var contactEditText: EditText*/
var TAG = HomeActivity::class.java.simpleName
    private lateinit var binding: ActivityHomeBinding

var b = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_home) //inflating
      /*  homeTextView = findViewById(R.id.tvHome)
        contactEditText = findViewById(R.id.etContact) //taking handle*/
        //get the intent which started this activity, get extras from intent and show it in a textview
        var mydata = intent.getStringExtra("co")
        //homeTextView.setText(mydata)
        binding.tvHome.setText(mydata)
    }

    override fun onStart() {
        super.onStart()
        var a = 10
        binding.btnJson.setOnClickListener {
            getJsonNet()
        }
    }

    private fun getJsonNet() {
    GlobalScope.launch {   //launch = coroutine

        val listResult = MarsApi.retrofitService.getPhotos()
        Log.i(TAG,listResult)

    }
    }

    fun sendContact(view: View) {
        //get the phno from the edittext
        var phnno = binding.etContact.text.toString()
        //put phno in an intet
        var intentReturned = Intent()
        intentReturned.putExtra("phno",phnno)
        //send it to parent/mainactivity
        setResult(RESULT_OK,intentReturned)//
        //close this activity
        finish()
    }
}