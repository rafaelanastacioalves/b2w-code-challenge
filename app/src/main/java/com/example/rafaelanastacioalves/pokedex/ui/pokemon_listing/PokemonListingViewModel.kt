package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.pokedex.domain.entities.MainEntity
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonListResponse
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.repository.AppRepository
import kotlinx.coroutines.launch


class PokemonListingViewModel : ViewModel() {

    private val appRepository: AppRepository = AppRepository
    val mainEntityListLiveData = MutableLiveData<Resource<List<PokemonReference>>>()

    fun loadDataIfNecessary(){
        if (mainEntityListLiveData.value == null){
            viewModelScope.launch {
                mainEntityListLiveData.postValue(appRepository.pokemonListResponse(100, 0))

            }
        }
    }

}
