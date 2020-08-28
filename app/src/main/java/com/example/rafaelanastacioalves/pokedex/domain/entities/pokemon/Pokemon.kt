package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pokemon(
        var id: Int,
        @SerializedName("base_experience")
        var baseExperience: Int,
        @ColumnInfo(name = "is_default")
        @SerializedName("is_default")
        var isDefault: Boolean,
        @SerializedName("location_area_encounters")
        var locationAreaEncounters: String,
        @Embedded
        var sprites: Sprites,
        var height: Int,
        var weight: Int,
        @PrimaryKey
        var name: String,
        var order: Int
) {
    lateinit var stats: ArrayList<Stat>
    lateinit var types: ArrayList<Type>
    lateinit var abilities: ArrayList<Ability>
}