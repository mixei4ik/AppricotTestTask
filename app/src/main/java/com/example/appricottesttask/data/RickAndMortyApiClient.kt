package com.example.appricottesttask.data

import com.example.appricottesttask.data.models.CharacterApi
import com.example.appricottesttask.data.models.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiClient {
    @GET("/api/character/")
    suspend fun getListOfCharacters(
        @Query("page") page: Int = 1
    ): CharacterApi
}

interface DetailCharacterApiClient {
    @GET("/api/character/{id}")
    suspend fun getDetailCharacters(
        @Path("id") id: Int
    ): Result
}