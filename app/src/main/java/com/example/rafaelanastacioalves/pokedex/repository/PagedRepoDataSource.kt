package com.example.rafaelanastacioalves.pokedex.repository

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagedRepoDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<String, PokemonReference>() {
    private val loadStatus: MutableLiveData<Boolean?> = MutableLiveData()
    private val INITIAL_OFFSET = 0
    companion object {
        val LIMIT = 100
    }
    private var CURRENT_OFFSET = 0
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    fun PagedRepoDataSource() {
        loadStatus.postValue(java.lang.Boolean.TRUE)
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PokemonReference>) {
        Log.d("PagedRepoDataSource", "LoadInitial")
        loadStatus.postValue(java.lang.Boolean.TRUE)
        scope.launch {
            val repositoryResource = appRepository.getPokemonReferenceList(LIMIT,INITIAL_OFFSET)
            when (repositoryResource.status) {
                Resource.Status.SUCCESS -> {

                    val mutableList = repositoryResource.data as MutableList<PokemonReference>
                    CURRENT_OFFSET += LIMIT
                    callback.onResult(mutableList,
                            "", (1+1).toString()                            )
                }
                Resource.Status.INTERNAL_SERVER_ERROR -> Log.e(javaClass.simpleName, repositoryResource.message)
                Resource.Status.GENERIC_ERROR -> Log.e(javaClass.simpleName, repositoryResource.message)
                Resource.Status.LOADING -> Log.e(javaClass.simpleName, repositoryResource.message)
            }
        }
    }

    fun getLiveLoadStatus(): MutableLiveData<Boolean?>? {
        return loadStatus
    }

    override fun loadAfter(@NonNull params: PageKeyedDataSource.LoadParams<String?>, @NonNull callback: PageKeyedDataSource.LoadCallback<String?, PokemonReference>) {
        Log.d("PagedRepoDataSource", "loadAfter")
        loadStatus.postValue(java.lang.Boolean.TRUE)
        scope.launch {

            val repositoryResource = appRepository.getPokemonReferenceList(LIMIT, CURRENT_OFFSET)
            when (repositoryResource.status) {
                Resource.Status.SUCCESS -> {
                    CURRENT_OFFSET += LIMIT
                    callback.onResult(
                            repositoryResource.data as MutableList<PokemonReference>,(Integer.valueOf(params.key) + 1).toString())
                }
                Resource.Status.INTERNAL_SERVER_ERROR -> Log.e(javaClass.simpleName, repositoryResource?.message)
                Resource.Status.GENERIC_ERROR -> Log.e(javaClass.simpleName, repositoryResource?.message)
                Resource.Status.LOADING -> Log.e(javaClass.simpleName, repositoryResource?.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PokemonReference>) {
    }


}

