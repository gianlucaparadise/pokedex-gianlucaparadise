package com.gianlucaparadise.pokedex.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.paging.PokemonListBoundaryCallback
import com.gianlucaparadise.pokedex.vo.Pokemon
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.CoroutineScope

interface PokedexRepository {
    fun getPokemonList(scope: CoroutineScope): Listing<PokemonListItem>
    suspend fun getPokemon(name: String): Pokemon
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

        // TODO: This LiveData should be replaced with Flow, but it is only possible with Paging3, which is in alpha
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagingConfig)

        val boundaryCallback = PokemonListBoundaryCallback(scope, database, backend, pagingConfig)
        livePagedListBuilder.setBoundaryCallback(boundaryCallback)

        val pagedList = livePagedListBuilder.build()

        return Listing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState
        )
    }

    override suspend fun getPokemon(name: String): Pokemon {

        val dbPokemon = database.pokemonDetailDao().getByName(name)
        if (dbPokemon != null) return dbPokemon

        val webPokemon = backend.getPokemon(name)
        database.pokemonDetailDao().insert(webPokemon)

        return webPokemon
    }
}