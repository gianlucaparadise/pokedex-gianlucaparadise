package com.gianlucaparadise.pokedex.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import com.gianlucaparadise.pokedex.vo.StatsDescriptor
import com.gianlucaparadise.pokedex.vo.Type
import com.gianlucaparadise.pokedex.vo.createStatsDescriptorFromRawStats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailViewModel(
    pokemonListItem: PokemonListItem,
    private val repository: PokedexRepository
) : ViewModel() {

    val name = pokemonListItem.name.capitalize()

    private val _types = MutableLiveData<List<Type>?>()
    val types: LiveData<List<Type>?> = _types

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _stats = MutableLiveData<StatsDescriptor>()
    val stats: LiveData<StatsDescriptor> = _stats

    init {
        viewModelScope.launch {
            try {
                val pokemon = withContext(Dispatchers.IO) {
                    repository.getPokemon(pokemonListItem.name)
                }
                _types.value = pokemon.types
                _imageUrl.value = pokemon.sprites?.front_default
                _stats.value = withContext(Dispatchers.IO) {
                    createStatsDescriptorFromRawStats(pokemon.stats)
                }

            } catch (ex: Exception) {
                Log.e("DetailViewModel", "Exception while retrieving pokemon details", ex)
            }
        }
    }
}