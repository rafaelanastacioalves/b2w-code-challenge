package com.example.rafaelanastacioalves.pokedex.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonListResponse
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference

@Dao
interface DAO {

    @Query("SELECT * FROM pokemonreference WHERE offset = :offset ORDER BY position")
    suspend fun getPokemonReferenceList(offset : Int): List<PokemonReference>

    @Insert
    suspend fun savePokemonReferenceList(listResponse: List<PokemonReference>?)
//
//    @Delete
//    fun delete(mainEntity: PokemonListResponse)
//
    @Query("DELETE FROM pokemonreference")
    fun deleteAllPokemonReferences()
}