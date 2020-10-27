package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

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
}