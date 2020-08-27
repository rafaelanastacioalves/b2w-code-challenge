package com.example.rafaelanastacioalves.pokedex.domain.entities

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    @SerializedName("results") val pokemonReferenceList: List<PokemonReference>
)