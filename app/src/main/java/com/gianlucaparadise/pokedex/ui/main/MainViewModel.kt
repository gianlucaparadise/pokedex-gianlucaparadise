package com.gianlucaparadise.pokedex.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokedexRepository) : ViewModel() {

    fun loadPokemonList() {
        viewModelScope.launch {
            val result = repository.getPokemonList(0, 100)

            Log.d(
                "MainViewModel",
                "getPokemon: count: ${result.count} first: ${result.results?.firstOrNull()?.name}"
            )
        }
    }
}