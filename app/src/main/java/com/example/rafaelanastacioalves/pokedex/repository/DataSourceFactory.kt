package com.example.rafaelanastacioalves.pokedex.repository;

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import kotlinx.coroutines.CoroutineScope


class DataSourceFactory(private val viewModelScope: CoroutineScope) : DataSource.Factory<String?, PokemonReference?>() {
    private val sourceLiveData: MutableLiveData<PagedRepoDataSource> = MutableLiveData()
    override fun create(): DataSource<String?, PokemonReference?>? {
        val source = PagedRepoDataSource(viewModelScope)
        sourceLiveData.postValue(source)
        return source
    }


}