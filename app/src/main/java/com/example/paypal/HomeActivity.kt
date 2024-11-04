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
import coil.load
import com.example.paypal.databinding.ActivityHomeBinding
import com.example.paypal.network.MarsApi
import kotlinx.coroutines.Dispatchers
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


    override fun onResume() {
        super.onResume()
        var a = 15
        var b = a * 20
        var c = sum(a,b)

        throw NullPointerException("debugginn demo")
    }

    fun sum(a:Int,b:Int):Int{
        return  a+b
    }

    private fun getJsonNet() {
    GlobalScope.launch(Dispatchers.Main) {   //launch = coroutine

      //  val listResult = MarsApi.retrofitService.getPhotos()
        val urlImage = MarsApi.retrofitService.getPhotos().get(0).imgSrc
        binding.imageView.load(urlImage)
        //touching ui immageview in bacckground thread -- not allowed

        Log.i(TAG,urlImage)

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