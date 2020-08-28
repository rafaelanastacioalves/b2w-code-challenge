package com.example.rafaelanastacioalves.pokedex.domain.interactors


import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.example.rafaelanastacioalves.pokedex.repository.AppRepository

class PokemonDetailsInteractor :
        Interactor<Resource<Pokemon>?, PokemonDetailsInteractor.RequestValues>() {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override suspend fun run(requestValue: PokemonDetailsInteractor.RequestValues?): Resource<Pokemon>? {
        var result = requestValue?.requestId?.let { appRepository.getPokemon(it) }
        return result
    }

    class RequestValues(val requestId: String) : Interactor.RequestValues

}
