package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.common.RestHelper.getStringFromFile
import com.example.rafaelanastacioalves.pokedex.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_detailing_fragment.*



class PokemonDetailingFragment : Fragment(), View.OnClickListener {
    lateinit private var mPokemonDetailingViewModel: PokemonDetailingViewModel
    lateinit private var pokemon : Pokemon
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemon = generateData()
//        loadData()
    }


    private fun generateData(): Pokemon {
        return Gson().fromJson(getStringFromFile(requireContext(), "pikachu_ok_response.json"), Pokemon::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflateViews(inflater, container)
    }


    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.pokemon_detailing_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(pokemon)
    }

    private fun bind(pokemon: Pokemon) {
        bindTypes(pokemon)
        bindAbilities(pokemon)
        bindStats(pokemon.stats)

    }

    private fun bindStats(stats: List<Stat>) {
        for (stat in stats){
            when (stat.stat.name){
                "hp" -> hpRatingBar.rating = convertToStar(stat.baseStat)
                "attack" -> attackRatingBar.rating = convertToStar(stat.baseStat)
                "defense" -> defenseRatingBar.rating = convertToStar(stat.baseStat)
                "special-attack" -> specialAttackRatingBar.rating = convertToStar(stat.baseStat)
                "special-defense" -> specialDefenseRatingBar.rating = convertToStar(stat.baseStat)
                "speed" -> speedRatingBar.rating = convertToStar(stat.baseStat)
            }

        }
    }

    private fun convertToStar(baseStat: Int): Float {
        return (baseStat.toFloat()/100)*5
    }

    private fun bindAbilities(pokemon: Pokemon) {
        var textView: TextView
        var i = 0
        for (item in pokemon.abilities) {
            textView = TextView(context)
            textView.text = item.ability.name
            textView.setPadding(8,8,8,8)
            textView.setOnClickListener { v ->
                Toast.makeText(context,
                        item.ability.name,
                        Toast.LENGTH_SHORT).show()
            }
            abilitiesGridLayout.addView(textView, i)
            i++
        }
    }

    private fun bindTypes(pokemon: Pokemon) {
        var textView: TextView
        var i = 0
        for (item in pokemon.types) {
            textView = TextView(context)
            textView.text = item.type.name
            textView.setPadding(4,4,4,4)
            typeGridLayout.addView(textView, i)
            i++
        }
    }

    private fun setupActionBarWithTitle(title: String) {
        val mActivity = activity as AppCompatActivity?
        val actionBar = mActivity!!.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title


        }
    }


    override fun onClick(v: View) {
    }

    companion object {
        var POKEMON_NAME: String? = null
    }


}// Required empty public constructor
