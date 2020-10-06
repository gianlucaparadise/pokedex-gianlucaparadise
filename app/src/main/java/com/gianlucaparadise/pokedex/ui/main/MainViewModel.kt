package com.gianlucaparadise.pokedex.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaparadise.pokedex.network.PokeApiService
import kotlinx.coroutines.launch

class MainViewModel(private val backend: PokeApiService) : ViewModel() {

    fun loadPokemonList() {
        viewModelScope.launch {
            val result = backend.getPokemonList(0, 100)

            Log.d("MainViewModel", "getPokemon: count: ${result.count} first: ${result.results?.firstOrNull()?.name}")
        }
    }
}