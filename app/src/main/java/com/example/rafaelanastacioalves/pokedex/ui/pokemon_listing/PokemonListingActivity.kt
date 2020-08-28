package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener
import com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing.PokemonDetailingActivity
import com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing.PokemonDetailingFragment
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
        animateIntro()
        configureSearch()
        subscribe()
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


    private fun subscribe() {
        mPokemonListingViewModel.pokemonReferenceLiveData.observeForever { returnedLIst ->
            pokemonListingAdapter.submitList(returnedLIst)
        }
    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        pokemonListingAdapter.setRecyclerViewClickListener(mClickListener)
        recyclerView.adapter = pokemonListingAdapter
    }




    override fun onClick(view: View, position: Int) {
        val pokemon = pokemonListingAdapter.currentList!![position]

        val intent = Intent(this, PokemonDetailingActivity::class.java)
        intent.putExtra(PokemonDetailingFragment.POKEMON_NAME, pokemon?.name)
        startActivity(intent)

    }

    private fun animateIntro() {
        recyclerView.itemAnimator = AnimationIntroAnimator()
    }


}
