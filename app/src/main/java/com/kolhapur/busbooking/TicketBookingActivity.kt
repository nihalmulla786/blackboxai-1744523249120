package com.kolhapur.busbooking

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kolhapur.busbooking.databinding.ActivityTicketBookingBinding

class TicketBookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val source = intent.getStringExtra("SOURCE") ?: ""
        val destination = intent.getStringExtra("DESTINATION") ?: ""
        val fare = intent.getIntExtra("FARE", 0)

        binding.tvRoute.text = "Route: $source to $destination"
        binding.tvFare.text = "Fare: ₹$fare"

        binding.btnBookTicket.setOnClickListener {
            val name = binding.etPassengerName.text.toString().trim()
            val age = binding.etPassengerAge.text.toString().trim()

            if (name.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Generate random ticket number
            val ticketNumber = (100000..999999).random()
            
            // Create booking confirmation message
            val message = """
                Ticket Booked Successfully!
                Ticket No: $ticketNumber
                Route: $source to $destination
                Passenger: $name (Age: $age)
                Fare: ₹$fare
            """.trimIndent()

            AlertDialog.Builder(this)
                .setTitle("Booking Confirmation")
                .setMessage(message)
                .setPositiveButton("OK") { _, _ -> finish() }
                .show()
        }
    }
}
