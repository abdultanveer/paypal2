package com.example.paypal

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paypal.database.Item
import com.example.paypal.database.ItemDao
import com.example.paypal.database.ItemRoomDatabase
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class DataActivity : AppCompatActivity() {

    lateinit var viewModel:DataViewModel
    var languages = arrayOf("english","hindi","tamil","telgu","kannada","urdu")
    lateinit var dao: ItemDao
   lateinit var tv: TextView
    lateinit var  recyclerView: RecyclerView
    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
       /* var myAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, //visiting card - 1tv
            android.R.id.text1,
            languages)*/

        var myAdapter = LangsAdapter(languages)
        recyclerView.adapter = myAdapter

        tv  = findViewById(R.id.tvResult)

       // tv.setText(""+viewModel.count)

        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()

        viewModel._seconds.observe(this, secsObserver);
    //me giving my phno to the postman


    }


    override fun onStart() {
        super.onStart()
         db = Firebase.firestore
        //query sms inbox
        val uriSms = Uri.parse("content://sms/inbox")
        val dataCursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)
        //put the queried data into ann adapter
        var fromColumnNames = arrayOf("address","body")
        var toTvResIds = intArrayOf(android.R.id.text1,android.R.id.text2)

        var adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            dataCursor,fromColumnNames,toTvResIds,0)

        var listView:ListView = findViewById(R.id.listView2)
        //set the adapter onto listview
        listView.adapter = adapter
    }

    fun commitData(view: View) {
        var gItem = Item(11, "fruits", 12.99, 12)
        GlobalScope.launch {
            dao.insert(gItem)
        }
    }

    fun getData(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            var item =  dao.getItem(11).first()
            tv.setText(item.itemName)
        }
    }

    fun incrementCount(view: View) {
        viewModel.incrementCountvar()
        tv.setText(""+viewModel.count)
    }

    fun beginTimer(view: View) {
        viewModel.startTimer()
    }

    var secsObserver: Observer<Int> = object : Observer<Int> {
        override fun onChanged(update: Int) {
            //receiving the update/notification
            tv.setText(update.toString())
        }
    }

    fun addFb(view: View) {
        val user = hashMapOf(
            "first" to "sangeeth",
            "last" to "abhi",
            "born" to 1815
        )

// Add a new document with a generated ID
        db.collection("students")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    companion object{
        var TAG = DataActivity::class.java.simpleName
    }

    fun getDataFb(view: View) {
        db.collection("students")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}