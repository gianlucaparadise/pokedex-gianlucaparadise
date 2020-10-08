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

    private val _pokemonList = MutableLiveData<List<PokemonListItem>>()
    val pokemonList: LiveData<List<PokemonListItem>> = _pokemonList

    fun loadPokemonList() {
        viewModelScope.launch {
            val result = repository.getPokemonList(0, 100)
            _pokemonList.value = result.results

            Log.d(
                "MainViewModel",
                "getPokemon: count: ${result.count} first: ${result.results?.firstOrNull()?.name}"
            )
        }
    }
}