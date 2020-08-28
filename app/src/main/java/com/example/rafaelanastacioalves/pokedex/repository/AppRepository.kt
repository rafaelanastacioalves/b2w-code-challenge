package com.example.rafaelanastacioalves.pokedex.repository

import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.example.rafaelanastacioalves.pokedex.repository.database.AppDataBase
import com.example.rafaelanastacioalves.pokedex.repository.database.DAO
import com.example.rafaelanastacioalves.pokedex.repository.http.APIClient
import com.example.rafaelanastacioalves.pokedex.repository.http.ServiceGenerator

object AppRepository {
    private val appDao: DAO = AppDataBase.getInstance().appDAO()
    var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);

    suspend fun getPokemonReferenceList(limit: Int, offset: Int): Resource<List<PokemonReference>> {
        return object : NetworkBoundResource<List<PokemonReference>, List<PokemonReference>>() {
            override suspend fun makeCall(): List<PokemonReference>? {

                val pokemonReferenceListResponse = apiClient.getPokemonReferenceList(offset, limit)
                return pokemonReferenceListResponse.pokemonReferenceList
            }

            override suspend fun getFromDB(): List<PokemonReference>? {
                val pokemonListReference = appDao.getPokemonReferenceList(offset)
                return if (pokemonListReference.isNullOrEmpty()) null else {
                    pokemonListReference
                }
            }

            override suspend fun saveIntoDB(resultData: List<PokemonReference>?) {
                for (i in 0 until limit) {
                    resultData?.get(i)?.apply {
                        this.offset = offset
                        position = i + offset
                    }
                }
                appDao.savePokemonReferenceList(resultData)
            }

        }.fromHttpAndDB()
    }
    
    suspend fun getPokemon(requestId: String): Resource<Pokemon> {
        return object : NetworkBoundResource<Pokemon, Pokemon>() {
            override suspend fun makeCall(): Pokemon? {
                return apiClient.getPokemon(requestId)
            }

            override suspend fun getFromDB(): Pokemon? {
                return appDao.getPokemon(requestId)
            }

            override suspend fun saveIntoDB(resultData: Pokemon?) {
                if (resultData != null) {
                    appDao.savePokemon(resultData)
                }
            }
        }.fromHttpAndDB()
    }
}