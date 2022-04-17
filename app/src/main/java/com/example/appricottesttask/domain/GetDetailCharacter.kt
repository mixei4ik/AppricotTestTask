package com.example.appricottesttask.domain

import com.example.appricottesttask.domain.models.DetailCharacter
import com.example.sevenwindsstudiotask.common.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailCharacter @Inject constructor(
    private val repository: RickAndMortyRepository
) {

    suspend fun getDetailCharacters(id: Int): Resource<DetailCharacter> {
        return try {
            val detailCharacters = repository.getDetailCharacter(id)
            Resource.Success(detailCharacters)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection")
        }
    }
}