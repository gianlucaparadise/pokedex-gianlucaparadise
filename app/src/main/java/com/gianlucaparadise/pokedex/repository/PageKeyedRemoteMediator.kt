package com.gianlucaparadise.pokedex.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.mapper.toPokemonListItemList
import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db: AppDatabase,
    private val backend: PokeApiService
) : RemoteMediator<Int, PokemonListItem>() {
    private val pokemonListItemDao = db.pokemonListItemDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonListItem>
    ): MediatorResult {
        try {
            Log.d("PageKeyedRemoteMediator", "loading - loadType: $loadType")
            val offset = when (loadType) {
                // When refreshing, I start loading from the beginning
                LoadType.REFRESH -> 0

                // We never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    // If lastItem is null it means no items were loaded after the initial REFRESH
                    // and there are no more items to load.
                    state.lastItemOrNull()?.id ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
            }

            val limit = when (loadType) {
                LoadType.REFRESH -> state.config.initialLoadSize
                else -> state.config.pageSize
            }

            Log.d("PageKeyedRemoteMediator", "retrieving - offset: $offset - limit: $limit")
            val data = backend.getPokemonList(offset, limit)

            val items = data.results?.toPokemonListItemList()

            if (items != null) {
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        // When refreshing, I'm starting from the beginning
                        pokemonListItemDao.clearAll()
                    }

                    pokemonListItemDao.insert(items)
                }
            }

            return MediatorResult.Success(endOfPaginationReached = items?.isEmpty() == true)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}