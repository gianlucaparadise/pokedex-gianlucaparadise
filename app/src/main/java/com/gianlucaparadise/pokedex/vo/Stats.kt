package com.gianlucaparadise.pokedex.vo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: NameUrlPair
)