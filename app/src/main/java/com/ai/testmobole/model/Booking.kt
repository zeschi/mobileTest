package com.ai.testmobole.model

data class Booking(
    val shipReference: String,
    val shipToken: String,
    val canIssueTicketChecking: Boolean,
    val expiryTime: Long, // Unix timestamp in seconds
    val duration: Int,
    val segments: List<Segment>
)

data class Segment(
    val id: Int,
    val originAndDestinationPair: OriginAndDestinationPair
)

data class OriginAndDestinationPair(
    val destination: Location,
    val destinationCity: String,
    val origin: Location,
    val originCity: String
)

data class Location(
    val code: String,
    val displayName: String,
    val url: String
)