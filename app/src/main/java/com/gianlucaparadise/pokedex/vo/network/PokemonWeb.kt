package com.gianlucaparadise.pokedex.vo.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonWeb(
    val id: Int,
    val name: String,
    val base_experience: Int?,
    val height: Int?,
    val is_default: Boolean?,
    val order: Int?,
    val weight: Int?,
    val location_area_encounters: String?,
    val sprites: SpritesWeb?,
    val stats: List<StatsWeb>?,
    val types: List<TypeWeb>?
)