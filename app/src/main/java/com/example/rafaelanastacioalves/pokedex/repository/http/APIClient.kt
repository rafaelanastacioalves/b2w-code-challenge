package com.example.rafaelanastacioalves.pokedex.repository.http;


import com.example.rafaelanastacioalves.pokedex.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.pokedex.domain.entities.MainEntity
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {

    @GET("/api/v2/pokemon")
    suspend fun getPokemonReferenceList(
            @Query("offset") position: Int,
            @Query("limit") limit: Int): PokemonListResponse

    @POST("/trip-packages/{entityID}")
    suspend fun getEntityDetails(@Path("entityID") id: String): EntityDetails

    @POST("/trip-packages-additional")
    suspend fun getMainEntityListAdditional(): List<MainEntity>

}
