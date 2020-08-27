package com.example.rafaelanastacioalves.pokedex.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonReference(
        @PrimaryKey val name: String,
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "offset") var offset : Int,
        @ColumnInfo(name = "position") var position : Int
)