package com.example.rafaelanastacioalves.pokedex.domain.entities

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)