package com.gianlucaparadise.pokedex.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokedexRepository) : ViewModel() {

    private val pokemonListResult = repository.getPokemonList(viewModelScope)

    val pokemonList = pokemonListResult.pagedList
    val networkState = pokemonListResult.networkState
}