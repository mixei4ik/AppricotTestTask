package com.example.appricottesttask.data

import com.example.appricottesttask.domain.RickAndMortyRepository
import com.example.appricottesttask.domain.models.Characters
import com.example.appricottesttask.domain.models.DetailCharacter
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApiService
) : RickAndMortyRepository {

    override suspend fun getCharacters(page: Int): List<Characters> {
        return api.getListOfCharacters(page)
            .results
            .map { result ->
                Characters(
                    id = result.id,
                    name = result.name,
                    species = result.species,
                    gender = result.gender,
                    image = result.image,

                    )
            }
    }

    override suspend fun getDetailCharacter(id: Int): DetailCharacter {
        val result = api.getDetailCharacter(id)
        return DetailCharacter(
            id = result.id,
            name = result.name,
            status = result.status,
            species = result.species,
            gender = result.gender,
            image = result.image,
            amountEpisode = result.episode.size,
            location = result.location.name
        )

    }
}