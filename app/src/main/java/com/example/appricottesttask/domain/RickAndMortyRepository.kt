package com.example.appricottesttask.domain

interface RickAndMortyRepository {

    suspend fun getCharacters(): List<Character>
}