package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.repository.DataSourceFactory
import com.example.rafaelanastacioalves.pokedex.repository.PagedRepoDataSource
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


class PokemonListingViewModel : ViewModel() {

    val pokemonReferenceLiveData: LiveData<PagedList<PokemonReference?>> by lazy {
        val dataSourceFactory = DataSourceFactory(viewModelScope)
        val mLivePagedListBuilder: LivePagedListBuilder<String?, PokemonReference?> = LivePagedListBuilder(dataSourceFactory, 5)
        mLivePagedListBuilder
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }
}
