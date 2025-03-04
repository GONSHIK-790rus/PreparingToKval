package com.example.preparingtokval.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.preparingtokval.App
import com.example.preparingtokval.R
import com.example.preparingtokval.data.models.Flight
import com.example.preparingtokval.databinding.ActivityFlightsBinding
import com.example.preparingtokval.ui.recyclerview.FlightAdapter
import com.google.gson.Gson
import kotlinx.coroutines.*

class FlightsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlightsBinding
    private val adapter = FlightAdapter()
    private lateinit var flights: List<Flight>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flights)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFlightsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.btnGetJsonData).setOnClickListener {
            lifecycleScope.launch {
                insertFlightDataToDataBase()
                flights = getFlights()
                adapter.initFlightsList(flights)
            }
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
        lifecycleScope.launch {
            flights = getFlights()
            initBinding()
        }
    }

    private fun insertFlightDataToDataBase() {
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

    private fun getFlights(): List<Flight> {
        val list: List<Flight>
        runBlocking {
            list = App.dataBase.flightDao().getAllFlights()
        }
        return list
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewAvailableFlights.layoutManager = GridLayoutManager(this@FlightsActivity, 1)
            recyclerViewAvailableFlights.adapter = adapter
            adapter.initFlightsList(flights)
        }
    }
}

data class Flights(
    val data: List<Flight>
)
