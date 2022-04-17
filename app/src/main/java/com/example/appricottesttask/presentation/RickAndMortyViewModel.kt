package com.example.appricottesttask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appricottesttask.domain.GetDetailCharacter
import com.example.appricottesttask.domain.GetRickAndMortyCharacters
import com.example.appricottesttask.domain.models.Characters
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

    private val _rickAndMortyCharacters = MutableLiveData<Resource<List<Characters>>>()
    val rickAndMortyCharacters: LiveData<Resource<List<Characters>>> = _rickAndMortyCharacters

    private val _detailCharacters = MutableLiveData<Resource<DetailCharacter>>()
    val detailCharacters: LiveData<Resource<DetailCharacter>> = _detailCharacters

    init {
        getCharacters(1)
    }

    private fun getCharacters(page: Int) {
        _rickAndMortyCharacters.value = Resource.Loading()
        viewModelScope.launch {
            _rickAndMortyCharacters.value = getRickAndMortyCharacters.getRickAndMortyCharacters(page)
        }
    }

    fun getDetailCharacter(id: Int) {
        _rickAndMortyCharacters.value = Resource.Loading()
        viewModelScope.launch {
            _detailCharacters.value = getDetailCharacter.getDetailCharacters(id)
        }
    }
}