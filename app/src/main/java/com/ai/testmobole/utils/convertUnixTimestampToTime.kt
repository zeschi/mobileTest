package com.ai.testmobole.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun convertUnixTimestampToTime(expiryTime: Long): String {
    // Step 1: Create an Instant from the Unix timestamp (in seconds)
    val instant = Instant.ofEpochSecond(expiryTime)
    
    // Step 2: Convert the Instant to a ZonedDateTime using the system's default time zone
    val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    
    // Step 3: Define the desired time format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    
    // Step 4: Format the ZonedDateTime into a readable string
    return zonedDateTime.format(formatter)
}