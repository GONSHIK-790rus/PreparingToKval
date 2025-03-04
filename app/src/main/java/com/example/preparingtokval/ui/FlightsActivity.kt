package com.example.preparingtokval.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.preparingtokval.App
import com.example.preparingtokval.R
import com.example.preparingtokval.data.models.Flight
import com.google.gson.Gson
import kotlinx.coroutines.*

class FlightsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flights)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnGetJsonData).setOnClickListener {
            insertFlightData()
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun insertFlightData() {
        val jsonString = applicationContext.assets.open("avia.json").bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val flights: Flights = gson.fromJson(jsonString, Flights::class.java)
        val flightsList: List<Flight> = flights.data

        insertFlightsToDataBase(flightsList)
    }

    private fun insertFlightsToDataBase(flightsList: List<Flight>){
        lifecycleScope.launch {
            try {
                App.dataBase.flightDao().addFlight(flightsList)
            } catch (e: Exception) {
                Toast.makeText(App.appContext, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}

data class Flights(
    val data: List<Flight>
)
