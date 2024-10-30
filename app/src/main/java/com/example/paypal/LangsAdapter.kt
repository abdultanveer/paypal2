package com.example.paypal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LangsAdapter(var languages: Array<String>) : RecyclerView.Adapter<LangsAdapter.LangsViewHolder>() {
    var TAG = LangsAdapter::class.java.simpleName


    class LangsViewHolder(visitingCard:View):RecyclerView.ViewHolder(visitingCard) {
        init {
            Log.i("langs","chirag is receiving the cards bought by saravanan")
        }
        var vcTextView: TextView = visitingCard.findViewById(R.id.tvVistingcard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangsViewHolder {
        Log.i(TAG,"saravannan bought a visiting card for row from market is giving to chirag ")
        var visitingCard = LayoutInflater.from(parent.context).inflate(R.layout.visiting_card,parent,false)
        return LangsViewHolder(visitingCard)
    }

    override fun getItemCount(): Int {
        Log.i(TAG,"sai priya-- counnting dataset--"+languages.size)
        return languages.size
    }

    override fun onBindViewHolder(holder: LangsViewHolder, position: Int) {
        Log.i(TAG,"srikanth is writing data on the visiting card-"+languages.get(position))
        holder.vcTextView.setText(languages.get(position))

    }
}