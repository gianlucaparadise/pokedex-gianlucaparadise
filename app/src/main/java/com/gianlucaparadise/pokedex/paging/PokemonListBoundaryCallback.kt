package com.gianlucaparadise.pokedex.paging

import android.util.Log
import androidx.paging.PagedList
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.Executors

class PokemonListBoundaryCallback(
    private val scope: CoroutineScope,
    private val db: AppDatabase,
    private val backend: PokeApiService,
    private val pageConfig: PagedList.Config
) :
    PagedList.BoundaryCallback<PokemonListItem>() {

    init {
        Log.d(tag, "new instance")
    }

    companion object {
        const val tag = "BoundaryCallback"
    }

    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    val networkState = helper.createStatusAsFlow()

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            scope.launch(executor.asCoroutineDispatcher()) {
                try {

                    Log.d(
                        tag, "Loading initial, start - " +
                                "pagesize: ${pageConfig.pageSize} "
                    )

                    val response =
                        backend.getPokemonList(offset = 0, limit = pageConfig.pageSize)

                    Log.d(
                        tag, "Loading initial, response - " +
                                "total: ${response.count} " +
                                "result size: ${response.results?.size} " +
                                "first: ${response.results?.firstOrNull()} "
                    )

                    val pokemonListItems = response.results
                    if (pokemonListItems != null) {
                        db.pokemonListItemDao().insert(pokemonListItems)
                    }

                    helperCallback.recordSuccess()

                } catch (e: Exception) {

                    Log.e(tag, "Error onZeroItemsLoaded", e)
                    helperCallback.recordFailure(e)
                }
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: PokemonListItem) {
        super.onItemAtEndLoaded(itemAtEnd)

        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            scope.launch(executor.asCoroutineDispatcher()) {
                try {

                    Log.d(
                        tag, "Loading after, start - " +
                                "page: ${itemAtEnd.name}, ${itemAtEnd.url} " +
                                "pagesize: ${pageConfig.pageSize} "
                    )

                    val offset = itemAtEnd.id // The item ID always equals i+1, where 'i' is the index of the item in the list
                    val response =
                        backend.getPokemonList(offset, limit = pageConfig.pageSize)

                    Log.d(
                        tag, "Loading after, response - " +
                                "total: ${response.count} " +
                                "result size: ${response.results?.size} " +
                                "first: ${response.results?.firstOrNull()} "
                    )

                    val pokemonListItems = response.results
                    if (pokemonListItems != null) {
                        db.pokemonListItemDao().insert(pokemonListItems)
                    }

                    helperCallback.recordSuccess()

                } catch (e: Exception) {

                    Log.e(tag, "Error onItemAtEndLoaded", e)
                    helperCallback.recordFailure(e)
                }
            }
        }
    }
}