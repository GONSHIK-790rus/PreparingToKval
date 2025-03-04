package com.example.preparingtokval.ui

import android.os.Bundle
import android.widget.Button
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlightsBinding
    private val adapter = FlightAdapter()
    private lateinit var flights: List<Flight>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favourites)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFlightsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            flights = getFavouriteFlights()
            initBinding()
        }
    }

    private fun getFavouriteFlights(): List<Flight> {
        val list: List<Flight>
        runBlocking {
            list = App.dataBase.favouriteDao().getCurrentUserFavourites(App.authorizedUser!!.id)
        }
        return list
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewAvailableFlights.layoutManager = GridLayoutManager(this@FavouritesActivity, 1)
            recyclerViewAvailableFlights.adapter = adapter
            adapter.initFlightsList(flights)
        }
    }
}