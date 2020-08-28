package com.example.rafaelanastacioalves.pokedex.repository.http;



import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonListResponse
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {

    @GET("/api/v2/pokemon")
    suspend fun getPokemonReferenceList(
            @Query("offset") position: Int,
            @Query("limit") limit: Int): PokemonListResponse

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): Pokemon
}
