package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@ForeignKey(
        entity = Pokemon::class,
        parentColumns = ["forms"],
        childColumns = ["pokemon_id"])
@Entity
data class Form(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val url: String,
        @ColumnInfo(name = "pokemon_id") @Transient
        var pokemonID: Int
)