package com.ai.testmobole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ai.testmobole.manager.DataManager
import com.ai.testmobole.service.BookingService

class MainActivity : AppCompatActivity() {
    private lateinit var dataManager: DataManager
    private lateinit var adapter: SegmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize DataManager
        dataManager = DataManager(this, BookingService(this))

        // Set up RecyclerView
        adapter = SegmentAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Refresh button
        findViewById<Button>(R.id.refresh_button).setOnClickListener {
            refreshData()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        try {
            val booking = dataManager.getBookingData()
            adapter.setSegments(booking.segments)
            Log.d("BookingActivity", "Booking Data: $booking")
        } catch (e: Exception) {
            Log.e("BookingActivity", "Error loading data", e)
            // Optionally, show error message to user
        }
    }

    private fun refreshData() {
        try {
            val booking = dataManager.refreshBookingData()
            adapter.setSegments(booking.segments)
            Log.d("BookingActivity", "Refreshed Booking Data: $booking")
        } catch (e: Exception) {
            Log.e("BookingActivity", "Error refreshing data", e)
            // Optionally, show error message to user
        }
    }
}