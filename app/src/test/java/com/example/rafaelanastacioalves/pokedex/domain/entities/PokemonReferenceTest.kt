package com.example.rafaelanastacioalves.pokedex.domain.entities

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PokemonReferenceTest {

    @Test
    fun should_giveBasicProperties_WhenPokemonReferenceCreatedOnlyWithTitle() {
        var testedPokemonReference: PokemonReference = createPokemonReference()
        assertThat(testedPokemonReference.position, `is`(equalTo(0)))
        assertThat(testedPokemonReference.offset, `is`(equalTo(0)))
        assertThat(testedPokemonReference.name, `is`(equalTo("pikachu")))
        assertThat(testedPokemonReference.url, `is`(equalTo("http://pikachu")))

    }

    private fun createPokemonReference(): PokemonReference {
        return PokemonReference("pikachu", "http://pikachu",0, 0)
    }


}