package com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon


import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

 class Sprites() {
    @ColumnInfo(name = "back_default")
    @SerializedName("back_default")
     var backDefault: String ? = null

    @ColumnInfo(name = "back_female")
    @SerializedName("back_female")
    var backFemale: String? = null

    @ColumnInfo(name = "back_shiny")
    @SerializedName("back_shiny")
     var backShiny: String? = null

    @ColumnInfo(name = "back_shiny_female")
    @SerializedName("back_shiny_female")
     var backShinyFemale: String? = null

    @ColumnInfo(name = "front_default")
    @SerializedName("front_default")
     var frontDefault: String? = null

    @ColumnInfo(name = "front_female")
    @SerializedName("front_female")
     var frontFemale: String? = null

    @ColumnInfo(name = "front_shiny")
    @SerializedName("front_shiny")
     var frontShiny: String? = null

    @ColumnInfo(name = "front_shiny_female")
    @SerializedName("front_shiny_female")
     var frontShinyFemale: String? = null
}