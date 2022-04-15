package com.example.appricottesttask.data

import com.example.appricottesttask.data.models.CharacterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyApiService @Inject constructor(
    private val apiRickAndMorty: RickAndMortyApiClient
) {

    suspend fun getListOfCharacters(): CharacterApi {
        return withContext(Dispatchers.IO) {
            apiRickAndMorty.getListOfCharacters()
        }
    }
}