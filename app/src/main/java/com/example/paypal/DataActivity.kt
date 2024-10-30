package com.example.paypal

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paypal.database.Item
import com.example.paypal.database.ItemDao
import com.example.paypal.database.ItemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataActivity : AppCompatActivity() {

    lateinit var dao: ItemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()

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
            var tv: TextView = findViewById(R.id.tvResult)
            tv.setText(item.itemName)
        }
    }
}