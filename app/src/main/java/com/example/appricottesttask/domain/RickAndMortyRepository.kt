package com.example.appricottesttask.domain

import com.example.appricottesttask.domain.models.Characters

interface RickAndMortyRepository {

    suspend fun getCharacters(): List<Characters>
}