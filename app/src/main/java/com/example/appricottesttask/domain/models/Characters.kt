package com.example.appricottesttask.domain.models

data class Characters(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val amountEpisode: Int,
    val location: String
)
