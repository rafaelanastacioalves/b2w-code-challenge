package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.example.rafaelanastacioalves.pokedex.domain.interactors.PokemonDetailsInteractor


internal class PokemonDetailingViewModel : ViewModel() {

    val entityDetails = MutableLiveData<Resource<Pokemon>>()

    val pokemonDetailsInteractor: PokemonDetailsInteractor = PokemonDetailsInteractor()

    fun loadData(entityId: String?) : MutableLiveData<Resource<Pokemon>> {

        entityDetails.postValue(Resource.loading())
        pokemonDetailsInteractor.execute(viewModelScope,
                entityId?.let{PokemonDetailsInteractor.RequestValues(it)},{ it -> handle(it)})
        return entityDetails
    }

    private fun handle(it: Resource<Pokemon>?) {
        entityDetails.postValue(it)
    }
}

