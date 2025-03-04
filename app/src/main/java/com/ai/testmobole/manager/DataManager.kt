package com.ai.testmobole.manager

import android.content.Context
import android.util.Log
import com.ai.testmobole.model.Booking
import com.ai.testmobole.service.BookingService
import com.google.gson.Gson
import java.io.File

class DataManager(private val context: Context, private val service: BookingService) {
    private val cacheFileName = "booking_cache.json"

    fun getBookingData(): Booking {
        try {
            // Check if valid cached data exists
            if (cacheExists()) {
                val jsonString = readFromCache()
                val booking = Gson().fromJson(jsonString, Booking::class.java)
                if (System.currentTimeMillis() / 1000 < booking.expiryTime) {
                    Log.d("DataManager", "Using cached data")
                    return booking
                } else {
                    Log.d("DataManager", "Cache expired")
                }
            }
        } catch (e: Exception) {
            Log.e("DataManager", "Error reading or parsing cache", e)
            // Proceed to fetch from service if cache fails
        }

        // Fetch new data from service and cache it
        val booking = service.getBookingData()
        saveToCache(Gson().toJson(booking))
        Log.d("DataManager", "Fetched new data from service")
        return booking
    }

    fun refreshBookingData(): Booking {
        try {
            val booking = service.getBookingData()
            saveToCache(Gson().toJson(booking))
            Log.d("DataManager", "Data refreshed")
            return booking
        } catch (e: Exception) {
            Log.e("DataManager", "Error refreshing data", e)
            throw e
        }
    }

    private fun cacheExists(): Boolean {
        val file = File(context.filesDir, cacheFileName)
        return file.exists()
    }

    private fun readFromCache(): String {
        val file = File(context.filesDir, cacheFileName)
        return file.readText()
    }

    private fun saveToCache(jsonString: String) {
        val file = File(context.filesDir, cacheFileName)
        file.writeText(jsonString)
    }
}