package com.gianlucaparadise.pokedex.vo.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatsWeb(
    val base_stat: Int,
    val effort: Int,
    val stat: NameUrlPair
)