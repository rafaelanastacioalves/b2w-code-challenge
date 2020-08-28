package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @ColumnInfo(name = "front_default") @SerializedName("front_default")
    val frontDefault: String
)