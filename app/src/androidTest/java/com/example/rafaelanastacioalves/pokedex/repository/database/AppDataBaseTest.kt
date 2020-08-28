package com.example.rafaelanastacioalves.pokedex.repository.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rafaelanastacioalves.pokedex.common.RestHelper
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.StringContains
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule();

    private val context: Context by lazy {
        ApplicationProvider.getApplicationContext() as Context
    }

    private val testedDAO: DAO by lazy {
        AppDataBase.getInstance().appDAO()
    }

    @Before
    fun setup() {
        AppDataBase.setupAtApplicationStartup(context)
    }

    @Test
    fun when_savingPokemonReferenceList_Should_ReturnPokemonReferenceList() {

        runBlocking {
            testedDAO.deleteAllPokemonReferences()
        }

        val pokemonReferenceList: List<PokemonReference> = Arrays.asList(
                PokemonReference("pikachu",
                        "http://1",
                        2,
                        0),
                PokemonReference("charizard",
                        "http://2",
                        2,
                        1)
        )
        runBlocking {
            testedDAO.savePokemonReferenceList(pokemonReferenceList)
        }

        lateinit var restoredPokemonReferenceList: List<PokemonReference>
        lateinit var restoredFirstPokemonReference: PokemonReference
        runBlocking {
            restoredPokemonReferenceList = testedDAO.getPokemonReferenceList(2)
            restoredFirstPokemonReference = restoredPokemonReferenceList.get(0)
        }


        if (restoredPokemonReferenceList != null || restoredFirstPokemonReference != null) {
            assertThat(restoredPokemonReferenceList.size, CoreMatchers.`is`(2))
            assertThat(restoredFirstPokemonReference.name, StringContains("pikachu"))
            assertThat(restoredFirstPokemonReference.position, CoreMatchers.`is`(0))
        }


    }

    @Test
    fun when_ThereIsNoPokemonReference_Should_Return_EmptyList() {

        lateinit var restoredMainEntityList: List<PokemonReference>
        runBlocking {
            testedDAO.deleteAllPokemonReferences()
            restoredMainEntityList = testedDAO.getPokemonReferenceList(2)
        }

        assertThat(restoredMainEntityList.size, CoreMatchers.`is`(0))
    }

    @Test
    fun when_savingPokemon_Should_returnPokemon() {
        runBlocking {
            testedDAO.deleteAllPokemon()
        }

        val pokemon = Gson().fromJson(RestHelper.getStringFromFile(context, "pikachu_ok_response.json"), Pokemon::class.java)
        lateinit var pokemonFromDB : Pokemon
        runBlocking {
            testedDAO.savePokemon(pokemon)
            pokemonFromDB = testedDAO.getPokemon("pikachu")
        }

        assertThat(pokemonFromDB.name, StringContains("pikachu"))
        checkNotNull(pokemonFromDB.types)
        checkNotNull(pokemonFromDB.sprites)
        checkNotNull(pokemonFromDB.stats)
        checkNotNull(pokemonFromDB.abilities)
    }

}