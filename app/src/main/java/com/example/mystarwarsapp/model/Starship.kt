package com.example.mystarwarsapp.model

data class Starship(val name:String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val cargo_capacity: String
)

data class SwapiResponse<T>(
    val count: Int,
    val next: String?, //URL to next page
    val previous: String?, //URL to previous page
    val results: List<T>
)
