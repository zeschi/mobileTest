package com.ai.testmobole.service

import android.content.Context
import com.ai.testmobole.model.Booking
import com.google.gson.Gson
import java.io.IOException

class BookingService(private val context: Context) {
    fun getBookingData(): Booking {
        try {
            val jsonString = context.assets.open("booking.json").bufferedReader().use { it.readText() }
            return Gson().fromJson(jsonString, Booking::class.java)
        } catch (e: IOException) {
            throw IOException("Failed to read booking.json from assets", e)
        } catch (e: Exception) {
            throw Exception("Failed to parse booking.json", e)
        }
    }
}