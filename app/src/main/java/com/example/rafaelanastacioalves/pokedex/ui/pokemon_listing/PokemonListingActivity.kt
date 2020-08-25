package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing


import android.content.Intent
import android.os.Build
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.domain.entities.MainEntity
import com.example.rafaelanastacioalves.pokedex.domain.entities.Result
import com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing.PokemonDetailingActivity
import com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing.PokemonDetailingFragment
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener
import kotlinx.android.synthetic.main.pokemon_listing_activity.*

class PokemonListingActivity : AppCompatActivity(), RecyclerViewClickListener{

    private val mClickListener = this
    private val pokemonListingAdapter by lazy {
            PokemonListingAdapter(this)
    }
    private val mPokemonListingViewModel: PokemonListingViewModel by lazy {
        ViewModelProvider(this).get(PokemonListingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupRecyclerView()
        configureSearch()
        loadData()

    }

    private fun configureSearch() {
        editSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                pokemonListingAdapter.filter(newText)
                return false
            }

        })
    }

    private fun loadData() {
        pokemonListingAdapter.setItems(generateData())
    }

    private fun generateData(): List<Result>? {
        val list : List<Result> = listOf(
                Result("wobbuffet", "https://pokeapi.co/api/v2/pokemon/202/"),
                Result("girafarig", "https://pokeapi.co/api/v2/pokemon/203/"),
                Result("pineco", "https://pokeapi.co/api/v2/pokemon/204/"),
                Result("forretress", "https://pokeapi.co/api/v2/pokemon/205/"),
                Result("dunsparce", "https://pokeapi.co/api/v2/pokemon/206/"),
                Result("gligar", "https://pokeapi.co/api/v2/pokemon/207/"),
                Result("steelix", "https://pokeapi.co/api/v2/pokemon/208/"),
                Result("snubbull", "https://pokeapi.co/api/v2/pokemon/209/"),
                Result("granbull", "https://pokeapi.co/api/v2/pokemon/210/"),
                Result("qwilfish", "https://pokeapi.co/api/v2/pokemon/211/"),
                Result("scizor", "https://pokeapi.co/api/v2/pokemon/212/"),
                Result("shuckle", "https://pokeapi.co/api/v2/pokemon/213/")
        )
        return list
    }


    private fun subscribe() {
        mPokemonListingViewModel.mainEntityListLiveData.observeForever(Observer { mainEntities ->
//            populateRecyclerView(mainEntities)
        })
    }

    private fun setupViews() {
        setContentView(R.layout.pokemon_listing_activity)

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        pokemonListingAdapter.setRecyclerViewClickListener(mClickListener)
        recyclerView.adapter = pokemonListingAdapter
    }





    override fun onClick(view: View, position: Int) {
        val pokemon = pokemonListingAdapter!!.getItems()!!.get(position)


        val intent = Intent(this, PokemonDetailingActivity::class.java)
        intent.putExtra(PokemonDetailingFragment.POKEMON_NAME, "pikachu")
        startActivity(intent)

    }

    private fun startActivityByVersion(mainEntity: MainEntity, transitionImageView: AppCompatImageView) {
        val i = Intent(this, PokemonDetailingActivity::class.java)
        i.putExtra(PokemonDetailingFragment.POKEMON_NAME, mainEntity.id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var bundle: Bundle? = null
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PokemonListingActivity,
                    transitionImageView, transitionImageView.transitionName).toBundle()
            startActivity(i, bundle)

        } else {
            startActivity(i)
        }
    }
}
