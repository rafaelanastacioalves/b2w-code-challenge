package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TypeDetails(
    val name: String,
    val url: String
)