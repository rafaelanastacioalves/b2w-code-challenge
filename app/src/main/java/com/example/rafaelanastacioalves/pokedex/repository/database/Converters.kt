package com.example.rafaelanastacioalves.pokedex.repository.database

import androidx.room.TypeConverter
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Ability
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Sprites
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Stat
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Type
import com.google.gson.Gson


class Converters {

    @TypeConverter
    fun fromTypeListToStringvalue(list : ArrayList<Type>): String = Gson().toJson(list)

    @TypeConverter
    fun fromAbilityListToStringvalue(list : ArrayList<Ability>): String = Gson().toJson(list)

    @TypeConverter
    fun fromStatListToStringvalue(list : ArrayList<Stat>): String = Gson().toJson(list)

    @TypeConverter
    fun fromSpritesToString(list : Sprites): String = Gson().toJson(list)




    @TypeConverter
    fun fromStringToTypeList(date: String) : ArrayList<Type> = Gson().fromJson(date, Array<Type>::class.java).toMutableList() as ArrayList<Type>

    @TypeConverter
    fun fromStringToAbilityList(date: String) : ArrayList<Ability> = Gson().fromJson(date, Array<Ability>::class.java).toMutableList() as ArrayList<Ability>

    @TypeConverter
    fun fromStringToStatList(date: String) : ArrayList<Stat> = Gson().fromJson(date, Array<Stat>::class.java).toMutableList() as ArrayList<Stat>

    @TypeConverter
    fun fromStringToSprites(date: String) : Sprites = Gson().fromJson(date, Sprites::class.java)

}