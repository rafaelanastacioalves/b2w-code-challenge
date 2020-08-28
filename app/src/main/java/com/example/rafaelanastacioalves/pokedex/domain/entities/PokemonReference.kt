package com.example.rafaelanastacioalves.pokedex.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonReference(
        @PrimaryKey val name: String,
        val url: String,
        var offset : Int,
        var position : Int
)