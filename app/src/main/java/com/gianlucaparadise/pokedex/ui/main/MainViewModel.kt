package com.gianlucaparadise.pokedex.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(repository: PokedexRepository) : ViewModel() {

    private val pokemonListResult = repository.getPokemonList(viewModelScope)

    val pokemonList = pokemonListResult.pagedList
    val networkState = pokemonListResult.networkState.asLiveData(Dispatchers.IO)
}