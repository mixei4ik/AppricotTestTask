package com.example.appricottesttask.domain

import com.example.appricottesttask.domain.models.Characters
import javax.inject.Inject

class GetRickAndMortyCharacters @Inject constructor(
    private val repository: RickAndMortyRepository
) {

    suspend fun getRickAndMortyCharacters(page: Int): List<Characters> {
        return repository.getCharacters(page)
    }
}