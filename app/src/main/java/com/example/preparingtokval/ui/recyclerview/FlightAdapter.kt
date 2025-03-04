package com.example.preparingtokval.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.preparingtokval.App
import com.example.preparingtokval.R
import com.example.preparingtokval.data.models.Flight
import com.example.preparingtokval.databinding.FlightCardBinding
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class FlightAdapter: RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {
    private var flights = listOf<Flight>()

    class FlightViewHolder(flightCard: View): RecyclerView.ViewHolder(flightCard) {
        private val flightCard = FlightCardBinding.bind(flightCard)
        @SuppressLint("SetTextI18n")
        fun bind(flight: Flight) = with(flightCard) {
            txtViewRoute.text = "${flight.startCity} (${flight.startCityCode.uppercase()}) -> ${flight.endCity} (${flight.endCityCode.uppercase()})"
            txtViewDateTime.text = "Туда: ${formatDateTime(flight.startDate)} | Обратно: ${formatDateTime(flight.endDate)}"
            txtViewPrice.text = "Цена: ${flight.price}р"
            txtViewFlightNumber.text = "Номер рейса: ${flight.searchToken}"

            val checkString: String?
            val userId = App.authorizedUser!!.id
            runBlocking {
                checkString = App.dataBase.favouriteDao()
                    .getFavouriteID(flight.searchToken, userId)
            }
            if (checkString.isNullOrEmpty()) {
                btnStar.setImageResource(android.R.drawable.btn_star_big_off)
                btnStar.tag = "off"
            }
            else {
                btnStar.setImageResource(android.R.drawable.btn_star_big_on)
                btnStar.tag = "on"
            }

            btnStar.setOnClickListener {
                if (btnStar.tag == "off") {
                    runBlocking {
                        App.dataBase.favouriteDao().addToFavourite(userId, flight.searchToken)
                    }
                    btnStar.setImageResource(android.R.drawable.btn_star_big_on)
                    btnStar.tag = "on"
                }
                else {
                    runBlocking {
                        App.dataBase.favouriteDao().removeFromFavourite(userId, flight.searchToken)
                    }
                    btnStar.setImageResource(android.R.drawable.btn_star_big_off)
                    btnStar.tag = "off"
                }
            }
        }

        @SuppressLint("WeekBasedYear")
        private fun formatDateTime(dateTime: String): String {
            val instant = Instant.parse(dateTime)
            return DateTimeFormatter.ofPattern("dd.MM.yyyy 'в' HH:mm")
                .format(instant.atZone(ZoneOffset.UTC))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val flightCard = LayoutInflater.from(parent.context).inflate(R.layout.flight_card, parent, false)
        return FlightViewHolder(flightCard)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(flights[position])
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initFlightsList(flights: List<Flight>) {
        this.flights = flights
        notifyDataSetChanged()
    }
}