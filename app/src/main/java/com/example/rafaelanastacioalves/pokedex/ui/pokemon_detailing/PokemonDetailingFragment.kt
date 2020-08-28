package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.common.RestHelper.getStringFromFile
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.pokemon_detailing_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailingFragment : Fragment(), View.OnClickListener {
    lateinit private var mPokemonDetailingViewModel: PokemonDetailingViewModel
    private val pokemonDetailingViewLogic: PokemonDetailingViewLogicHelper = PokemonDetailingViewLogicHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pokemon = generateData()
        loadData()
    }




//    private fun generateData(): Pokemon {
//        return Gson().fromJson(getStringFromFile(requireContext(), "pikachu_ok_response.json"), Pokemon::class.java)
//    }

    private fun loadData() {
        val pokemonName = arguments!!.getString(POKEMON_NAME)
        mPokemonDetailingViewModel = ViewModelProvider.NewInstanceFactory().create(PokemonDetailingViewModel::class.java)
        mPokemonDetailingViewModel.loadData(pokemonName).observe(this, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        pokemonDetailingViewLogic.bind(it)
                        setupActionBarWithTitle(it.name)
                    }

                }
//                Resource.Status.INTERNAL_SERVER_ERROR -> TODO()
//                Resource.Status.GENERIC_ERROR -> TODO()
//                Resource.Status.LOADING -> TODO()
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflateViews(inflater, container)
        return views
    }

    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.pokemon_detailing_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter: PokemonImageAdapter = PokemonImageAdapter()
        imageViewPager.adapter = adapter
        TabLayoutMediator(tabLayout, imageViewPager, { tab, position ->

        }).attach()
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
