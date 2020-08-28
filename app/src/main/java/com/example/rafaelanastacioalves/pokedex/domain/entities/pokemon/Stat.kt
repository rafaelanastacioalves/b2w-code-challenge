package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    @Embedded
    val stat: StatX
)