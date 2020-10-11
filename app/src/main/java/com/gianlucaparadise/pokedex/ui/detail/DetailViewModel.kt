package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import io.uniflow.android.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class DetailViewModel(
    private val pokemonListItem: PokemonListItem,
    private val repository: PokedexRepository
) : AndroidDataFlow(defaultState = pokemonListItem.mapToDetailState()) {

    fun getPokemonDetail() = action(
        onAction = {
            val pokemon = repository.getPokemon(pokemonListItem.name)

            setState {
                pokemon.mapToDetailState()
            }
        },
        onError = { exception, _ ->
            Log.e("DetailViewModel", "getPokemonDetail: Error happened", exception)
            setState {
                UIState.Failed("Failed while retrieving pokemon detail", exception)
            }
        }
    )
}