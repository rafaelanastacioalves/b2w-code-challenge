package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Type(
    val slot: Int,
    @Embedded
    val type: TypeDetails
)