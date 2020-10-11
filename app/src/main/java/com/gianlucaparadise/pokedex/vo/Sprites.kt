package com.gianlucaparadise.pokedex.vo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sprites(
    val back_female: String?,
    val back_shiny_female: String?,
    val back_default: String?,
    val front_female: String?,
    val front_shiny_female: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?
)