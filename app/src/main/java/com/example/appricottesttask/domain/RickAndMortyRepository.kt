package com.example.appricottesttask.domain

import com.example.appricottesttask.domain.models.Characters
import com.example.appricottesttask.domain.models.DetailCharacter

interface RickAndMortyRepository {

    suspend fun getCharacters(page: Int): List<Characters>

    suspend fun getDetailCharacter(id: Int): DetailCharacter
}