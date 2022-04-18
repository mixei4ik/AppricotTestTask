package com.example.appricottesttask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.appricottesttask.domain.GetDetailCharacter
import com.example.appricottesttask.domain.GetRickAndMortyCharacters
import com.example.appricottesttask.domain.models.DetailCharacter
import com.example.sevenwindsstudiotask.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val getRickAndMortyCharacters: GetRickAndMortyCharacters,
    private val getDetailCharacter: GetDetailCharacter
) : ViewModel() {

    private val _detailCharacters = MutableLiveData<Resource<DetailCharacter>>()
    val detailCharacters: LiveData<Resource<DetailCharacter>> = _detailCharacters

    val rickAndMortyCharacters = Pager(config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = true
    ),
        pagingSourceFactory = { RickAndMortyPagingSource(getRickAndMortyCharacters) }).flow.cachedIn(
        viewModelScope
    )

    fun getDetailCharacter(id: Int) {
        _detailCharacters.value = Resource.Loading()
        viewModelScope.launch {
            _detailCharacters.value = getDetailCharacter.getDetailCharacters(id)
        }
    }
}