package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel(
    pokemonListItem: PokemonListItem,
    private val repository: PokedexRepository
) : ViewModel() {

    val name = pokemonListItem.name

    private val _oneLineType = MutableLiveData<String>()
    val oneLineType: LiveData<String> = _oneLineType

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    init {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemon(name)
                _oneLineType.value = pokemon.types?.joinToString(separator = ", ") { it.type.name }
                _imageUrl.value = pokemon.sprites?.front_default

            } catch (ex: Exception) {
                Log.d("DetailViewModel", "Exception while retrieving pokemon details")
            }
        }
    }
}