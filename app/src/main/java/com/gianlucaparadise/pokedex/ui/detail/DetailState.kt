package com.gianlucaparadise.pokedex.ui.detail

import com.gianlucaparadise.pokedex.vo.*
import io.uniflow.core.flow.data.UIState

fun Pokemon.mapToDetailState() = DetailState(
    name = name.capitalize(),
    types = types,
    imageUrl = sprites?.front_default,
    stats = createStatsDescriptorFromRawStats(stats),
)

fun PokemonListItem.mapToDetailState() = DetailState(
    name = name.capitalize(),
    types = null,
    imageUrl = null,
    stats = null,
)

data class DetailState(
    val name: String,
    val types: List<Type>?,
    val imageUrl: String?,
    val stats: StatsDescriptor?
) : UIState()