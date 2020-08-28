package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Other(
        @Embedded @SerializedName("dream_world")
        val dreamWorld: DreamWorld,
        @Embedded @SerializedName("official-artwork")
        val officialArtwork: OfficialArtwork
)