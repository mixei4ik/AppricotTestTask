package com.example.appricottesttask.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.appricottesttask.domain.GetRickAndMortyCharacters
import com.example.appricottesttask.domain.models.Characters
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RickAndMortyPagingSource @Inject constructor(
    private val myBackend: GetRickAndMortyCharacters,
) : PagingSource<Int, Characters>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> = try {
        val pageNumber = params.key ?: 1

        val response = myBackend.getRickAndMortyCharacters(page = pageNumber)

        val prevKey = if (pageNumber > 1) pageNumber - 1 else null
        val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

        LoadResult.Page(
            data = response,
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        return state.anchorPosition
    }
}