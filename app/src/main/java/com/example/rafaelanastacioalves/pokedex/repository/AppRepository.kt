package com.example.rafaelanastacioalves.pokedex.repository

import com.example.rafaelanastacioalves.pokedex.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.pokedex.domain.entities.MainEntity
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.repository.database.AppDataBase
import com.example.rafaelanastacioalves.pokedex.repository.database.DAO
import com.example.rafaelanastacioalves.pokedex.repository.http.APIClient
import com.example.rafaelanastacioalves.pokedex.repository.http.ServiceGenerator

object AppRepository {
    private val appDao: DAO = AppDataBase.getInstance().appDAO()
    var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);

    suspend fun pokemonListResponse(limit: Int, offset: Int): Resource<List<PokemonReference>> {
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
                for (i in offset until (offset + limit)) {
                    resultData?.get(i)?.apply {
                        this.offset = offset
                        position = i
                    }
                }
                appDao.savePokemonReferenceList(resultData)
            }

        }.fromHttpAndDB()
    }


}