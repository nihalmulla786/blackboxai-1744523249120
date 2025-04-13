package com.kolhapur.busbooking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kolhapur.busbooking.databinding.ActivityBusRoutesBinding

class BusRoutesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusRoutesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusRoutesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample bus stops in Kolhapur
        val busStops = arrayOf(
            "Shivaji University", "Rankala", "Dasara Chowk", 
            "Railway Station", "CST Circle", "Tarabai Park"
        )

        ArrayAdapter(this, android.R.layout.simple_spinner_item, busStops).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSource.adapter = adapter
            binding.spinnerDestination.adapter = adapter
        }

        binding.btnSearch.setOnClickListener {
            val source = binding.spinnerSource.selectedItem.toString()
            val destination = binding.spinnerDestination.selectedItem.toString()
            
            if (source == destination) {
                Toast.makeText(this, "Source and destination cannot be same", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculate fare (sample logic - 10rs per stop difference)
            val sourceIndex = busStops.indexOf(source)
            val destIndex = busStops.indexOf(destination)
            val fare = kotlin.math.abs(sourceIndex - destIndex) * 10

            binding.tvFare.text = "Fare: ₹$fare"
            binding.tvFare.visibility = android.view.View.VISIBLE
            binding.btnBookTicket.visibility = android.view.View.VISIBLE

            binding.btnBookTicket.setOnClickListener {
                val intent = Intent(this, TicketBookingActivity::class.java).apply {
                    putExtra("SOURCE", source)
                    putExtra("DESTINATION", destination)
                    putExtra("FARE", fare)
                }
                startActivity(intent)
            }
        }
    }
}
