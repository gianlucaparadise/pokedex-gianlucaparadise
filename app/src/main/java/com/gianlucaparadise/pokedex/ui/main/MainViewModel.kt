package com.gianlucaparadise.pokedex.ui.main

import androidx.lifecycle.*
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class MainViewModel(repository: PokedexRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pokemonList = repository.getPokemonList()
}