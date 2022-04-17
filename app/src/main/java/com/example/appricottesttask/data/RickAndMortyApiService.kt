package com.example.appricottesttask.data

import com.example.appricottesttask.data.models.CharacterApi
import com.example.appricottesttask.data.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyApiService @Inject constructor(
    private val apiRickAndMorty: RickAndMortyApiClient,
    private val detailCharacterApiClient: DetailCharacterApiClient
) {

    suspend fun getListOfCharacters(page: Int): CharacterApi {
        return withContext(Dispatchers.IO) {
            apiRickAndMorty.getListOfCharacters(page)
        }
    }

    suspend fun getDetailCharacter(id: Int): Result {
        return withContext(Dispatchers.IO) {
            detailCharacterApiClient.getDetailCharacters(id)
        }
    }
}