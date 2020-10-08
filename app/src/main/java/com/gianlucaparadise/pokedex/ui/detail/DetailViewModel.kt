package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gianlucaparadise.pokedex.vo.PokemonListItem

class DetailViewModel(pokemonListItem: PokemonListItem) : ViewModel() {

    init {
        Log.d("DetailViewModel", "PokemonListItem: $pokemonListItem")
    }

    val name = pokemonListItem.name

}