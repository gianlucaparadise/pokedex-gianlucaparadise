package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.Pokemon
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.*

class DetailViewModel(
    private val pokemonListItem: PokemonListItem,
    private val repository: PokedexRepository
) : AndroidDataFlow(defaultState = pokemonListItem.mapToDetailState()) {

    fun getPokemonDetail() = action(
        onAction = {
            sendEvent(DetailEvent.Loading)
            val pokemon = repository.getPokemon(pokemonListItem.name)

            setState {
                pokemon.mapToDetailState()
            }
            sendEvent(DetailEvent.NotLoading)
        },
        onError = { exception, _ ->
            Log.e("DetailViewModel", "getPokemonDetail: Error happened", exception)
            setState {
                UIState.Failed("Failed while retrieving pokemon detail", exception)
            }
            sendEvent(DetailEvent.NotLoading)
        }
    )

    /**
     * This function waits a while before returning the pokemon
     */
    private suspend fun getPokemonCalmly(pokemonName: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val minimumTime = async { delay(500) }
            val getPokemon = async { repository.getPokemon(pokemonName) }

            minimumTime.await()
            getPokemon.await()
        }
    }
}