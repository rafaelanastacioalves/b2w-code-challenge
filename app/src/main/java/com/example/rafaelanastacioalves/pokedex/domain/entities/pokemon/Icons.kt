package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: String
)