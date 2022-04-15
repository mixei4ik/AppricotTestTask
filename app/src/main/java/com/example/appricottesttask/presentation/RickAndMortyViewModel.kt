package com.example.appricottesttask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appricottesttask.domain.GetRickAndMortyCharacters
import com.example.appricottesttask.domain.models.Characters
import com.example.sevenwindsstudiotask.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val getRickAndMortyCharacters: GetRickAndMortyCharacters
) : ViewModel() {

    private val _rickAndMortyCharacters = MutableLiveData<Resource<List<Characters>>>()
    val rickAndMortyCharacters: LiveData<Resource<List<Characters>>> = _rickAndMortyCharacters

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _rickAndMortyCharacters.value = Resource.Loading()
        viewModelScope.launch {
            _rickAndMortyCharacters.value = getRickAndMortyCharacters.getRickAndMortyCharacters()
        }
    }
}