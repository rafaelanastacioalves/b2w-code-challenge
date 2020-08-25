package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)