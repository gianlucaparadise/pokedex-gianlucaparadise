package com.gianlucaparadise.pokedex.vo

data class Pokemon(
    val id: Int,
    val name: String,
    val base_experience: Int?,
    val height: Int?,
    val is_default: Boolean?,
    val order: Int?,
    val weight: Int?,
    val location_area_encounters: String?,
    val sprites: Sprites?,
    val stats: List<Stats>?,
    val types: List<Type>?
)