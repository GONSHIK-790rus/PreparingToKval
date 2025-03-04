package com.example.preparingtokval.ui.recyclerview

import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.preparingtokval.data.models.Flight

class FlightAdapter: RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {

    class FlightViewHolder(flightCard: View): RecyclerView.ViewHolder(flightCard) {
        fun bind(flight: Flight) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}