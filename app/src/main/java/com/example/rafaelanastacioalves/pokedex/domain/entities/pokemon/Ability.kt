package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Ability(
        @Embedded
        val ability: AbilityX,
        @SerializedName("is_hidden")
        val isHidden: Boolean,
        val slot: Int
)