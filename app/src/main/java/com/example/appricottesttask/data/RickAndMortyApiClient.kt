package com.example.appricottesttask.data

import com.example.appricottesttask.data.models.CharacterApi
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiClient {
    @GET("/api/character")
    suspend fun getListOfCharacters(): CharacterApi
}