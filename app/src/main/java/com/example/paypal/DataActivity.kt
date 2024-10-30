package com.example.paypal

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paypal.database.Item
import com.example.paypal.database.ItemDao
import com.example.paypal.database.ItemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataActivity : AppCompatActivity() {
    lateinit var viewModel:DataViewModel
    var languages = arrayOf("english","hindi","tamil","telgu","kannada","urdu")
    lateinit var dao: ItemDao
   lateinit var tv: TextView
    lateinit var  languagesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        languagesListView = findViewById(R.id.listView)

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        var myAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, //visiting card - 1tv
            android.R.id.text1,
            languages)
        languagesListView.adapter = myAdapter
        tv  = findViewById(R.id.tvResult)

       // tv.setText(""+viewModel.count)

        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()

        viewModel._seconds.observe(this, secsObserver);
    //me giving my phno to the postman


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
}