package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class DreamWorld(
    @SerializedName("front_default")
    val frontDefault: String,
    @Embedded @SerializedName("front_female")
    val frontFemale: String
)