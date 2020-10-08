package com.gianlucaparadise.pokedex.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.paging.PokemonListBoundaryCallback
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.CoroutineScope

interface PokedexRepository {
    fun getPokemonList(scope: CoroutineScope): Listing<PokemonListItem>
}

class PokedexRepositoryImpl(
    private val backend: PokeApiService,
    private val database: AppDatabase
) : PokedexRepository {

    private val pagingConfig = PagedList.Config.Builder()
        .setPageSize(30)
        .setInitialLoadSizeHint(30)
        .setEnablePlaceholders(false)
        .build()

    override fun getPokemonList(scope: CoroutineScope): Listing<PokemonListItem> {

        val dataSourceFactory = database.pokemonListItemDao().getAll()

        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagingConfig)

        val boundaryCallback = PokemonListBoundaryCallback(scope, database, backend, pagingConfig)
        livePagedListBuilder.setBoundaryCallback(boundaryCallback)

        val pagedList = livePagedListBuilder.build()

        return Listing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState
        )
    }
}