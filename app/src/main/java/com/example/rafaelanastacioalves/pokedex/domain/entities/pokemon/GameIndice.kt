package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    @Embedded
    val version: Version
)