package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing

import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Sprites
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Stat
import kotlinx.android.synthetic.main.pokemon_detailing_fragment.*

class PokemonDetailingViewLogicHelper(private val fragment: Fragment) {

    fun bind(pokemon: Pokemon) {
        bindTypes(pokemon)
        bindAbilities(pokemon)
        bindStats(pokemon.stats)
        bindPhotos(pokemon.sprites)
    }

    fun bindStats(stats: List<Stat>) {
        for (stat in stats) {
            when (stat.stat.name) {
                "hp" -> fragment.hpRatingBar.rating = convertToStar(stat.baseStat)
                "attack" -> fragment.attackRatingBar.rating = convertToStar(stat.baseStat)
                "defense" -> fragment.defenseRatingBar.rating = convertToStar(stat.baseStat)
                "special-attack" -> fragment.specialAttackRatingBar.rating = convertToStar(stat.baseStat)
                "special-defense" -> fragment.specialDefenseRatingBar.rating = convertToStar(stat.baseStat)
                "speed" -> fragment.speedRatingBar.rating = convertToStar(stat.baseStat)
            }

        }
    }

    fun bindAbilities(pokemon: Pokemon) {
        var textView: TextView
        var i = 0
        for (item in pokemon.abilities) {
            textView = TextView(fragment.context)
            textView.text = item.ability.name
            textView.setPadding(8, 8, 8, 8)
            textView.setOnClickListener { v ->
                Toast.makeText(fragment.context,
                        item.ability.name,
                        Toast.LENGTH_SHORT).show()
            }
            fragment.abilitiesGridLayout.addView(textView, i)
            i++
        }
    }

    fun bindTypes(pokemon: Pokemon) {
        var textView: TextView
        var i = 0
        for (item in pokemon.types) {
            textView = TextView(fragment.context)
            textView.text = item.type.name
            textView.setPadding(4, 4, 4, 4)
            fragment.typeGridLayout.addView(textView, i)
            i++
        }
    }

    fun bindPhotos(sprites: Sprites) {
        (fragment.imageViewPager.adapter as PokemonImageAdapter).apply {

            list = listOfNotNull(sprites.frontDefault,
                    sprites.backDefault,
                    sprites.frontShiny,
                    sprites.backShiny,
                    sprites.frontFemale,
                    sprites.backFemale,
                    sprites.frontShinyFemale,
                    sprites.backShinyFemale)
            notifyDataSetChanged()
        }

    }

    private fun convertToStar(baseStat: Int): Float {
        return (baseStat.toFloat() / 100) * 5
    }
}