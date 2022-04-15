package com.example.appricottesttask.domain

import com.example.appricottesttask.domain.models.Characters
import com.example.sevenwindsstudiotask.common.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRickAndMortyCharacters @Inject constructor(
    private val repository: RickAndMortyRepository
) {

    suspend fun getRickAndMortyCharacters(): Resource<List<Characters>> {
        return try {
            val rickAndMortyCharacters = repository.getCharacters()
            Resource.Success(rickAndMortyCharacters)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection")
        }
    }
}