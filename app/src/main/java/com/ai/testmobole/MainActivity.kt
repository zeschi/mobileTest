package com.ai.testmobole

import SegmentAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ai.testmobole.manager.DataManager
import com.ai.testmobole.service.BookingService
import com.ai.testmobole.utils.convertUnixTimestampToTime

class MainActivity : AppCompatActivity() {
    private lateinit var dataManager: DataManager
    private lateinit var adapter: SegmentAdapter

    private lateinit var tvShipReference:TextView

    private lateinit var tvExpiryTime:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initManager()
        initView()
    }

    // Initialize DataManager
    private fun initManager() {
        dataManager = DataManager(this, BookingService(this))
    }

    // Init View
    private fun initView() {
        // Set up RecyclerView
        adapter = SegmentAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Refresh button
        findViewById<Button>(R.id.refresh_button).setOnClickListener {
            refreshData()
        }
        tvShipReference = findViewById(R.id.tv_shipReference)
        tvExpiryTime = findViewById(R.id.tv_expiryTime)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        try {
            val booking = dataManager.getBookingData()
            tvShipReference.text="shipReference: ${booking.shipReference}"
            tvExpiryTime.text="expiryTime: ${convertUnixTimestampToTime( booking.expiryTime)}"
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