package com.gianlucaparadise.pokedex.ui.main

import androidx.lifecycle.*
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(repository: PokedexRepository) : ViewModel() {

    private val pokemonListResult = repository.getPokemonList(viewModelScope)

    val pokemonList = pokemonListResult.pagedList
    val networkState = pokemonListResult.networkState.asLiveData(Dispatchers.IO)
}