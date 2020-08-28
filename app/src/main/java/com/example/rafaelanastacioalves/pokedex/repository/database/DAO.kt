package com.example.rafaelanastacioalves.pokedex.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Form
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Type
import kotlin.collections.ArrayList

@Dao
interface DAO {

    @Query("SELECT * FROM pokemonreference WHERE offset = :offset ORDER BY position")
    suspend fun getPokemonReferenceList(offset : Int): List<PokemonReference>

    @Insert
    suspend fun savePokemonReferenceList(listResponse: List<PokemonReference>?)

    @Query("SELECT * FROM pokemon WHERE name = :name")
    suspend fun getPokemon(name : String) : Pokemon

    @Insert
    suspend fun savePokemon(pokemon : Pokemon)

    @Query("DELETE FROM pokemonreference")
    fun deleteAllPokemonReferences()

    @Query("DELETE FROM pokemon")
    fun deleteAllPokemon()


}