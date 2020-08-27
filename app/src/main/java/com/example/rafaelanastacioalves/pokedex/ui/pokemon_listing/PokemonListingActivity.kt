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
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
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
        setContentView(R.layout.pokemon_listing_activity)
        setupRecyclerView()
        configureSearch()
        subscribe()
        mPokemonListingViewModel.loadDataIfNecessary()
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

//    private fun loadData() {
//        pokemonListingAdapter.setItems(generateData())
//    }

//    private fun generateData(): List<PokemonReference>? {
//        val list : List<PokemonReference> = listOf(
//                PokemonReference("wobbuffet", "https://pokeapi.co/api/v2/pokemon/202/"),
//                PokemonReference("girafarig", "https://pokeapi.co/api/v2/pokemon/203/"),
//                PokemonReference("pineco", "https://pokeapi.co/api/v2/pokemon/204/"),
//                PokemonReference("forretress", "https://pokeapi.co/api/v2/pokemon/205/"),
//                PokemonReference("dunsparce", "https://pokeapi.co/api/v2/pokemon/206/"),
//                PokemonReference("gligar", "https://pokeapi.co/api/v2/pokemon/207/"),
//                PokemonReference("steelix", "https://pokeapi.co/api/v2/pokemon/208/"),
//                PokemonReference("snubbull", "https://pokeapi.co/api/v2/pokemon/209/"),
//                PokemonReference("granbull", "https://pokeapi.co/api/v2/pokemon/210/"),
//                PokemonReference("qwilfish", "https://pokeapi.co/api/v2/pokemon/211/"),
//                PokemonReference("scizor", "https://pokeapi.co/api/v2/pokemon/212/"),
//                PokemonReference("shuckle", "https://pokeapi.co/api/v2/pokemon/213/")
//        )
//        return list
//    }


    private fun subscribe() {
        mPokemonListingViewModel.mainEntityListLiveData.observeForever(Observer { resource ->
            when(resource.status){
                Resource.Status.SUCCESS -> pokemonListingAdapter.setItems(resource.data)
//                Resource.Status.INTERNAL_SERVER_ERROR -> TODO()
//                Resource.Status.GENERIC_ERROR -> TODO()
//                Resource.Status.LOADING -> TODO()
            }
        })
    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        pokemonListingAdapter.setRecyclerViewClickListener(mClickListener)
        recyclerView.adapter = pokemonListingAdapter
    }


//    private fun populateRecyclerView(list: Resource<List<MainEntity>>?) {
//        if (list == null) {
//            pokemonListingAdapter!!.setItems(null)
//
//        } else if (list.data!=null) {
//            pokemonListingAdapter!!.setItems(list.data)
//        }
//
//    }


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
