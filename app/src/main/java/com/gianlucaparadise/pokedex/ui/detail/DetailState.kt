package com.gianlucaparadise.pokedex.ui.detail

import com.gianlucaparadise.pokedex.vo.main.Pokemon
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import com.gianlucaparadise.pokedex.vo.main.StatsDescriptor
import io.uniflow.core.flow.data.UIState

fun Pokemon.mapToDetailState() = DetailState(
    name = name.capitalize(),
    types = types,
    imageUrl = sprites?.frontDefault,
    stats = stats,
)

fun PokemonListItem.mapToDetailState() = DetailState(
    name = name.capitalize(),
    types = null,
    imageUrl = null,
    stats = null,
)

data class DetailState(
    val name: String,
    val types: List<String>?,
    val imageUrl: String?,
    val stats: StatsDescriptor?
) : UIState()