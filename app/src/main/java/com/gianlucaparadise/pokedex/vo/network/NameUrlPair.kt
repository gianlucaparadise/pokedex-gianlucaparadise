package com.gianlucaparadise.pokedex.vo.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameUrlPair(
    val name: String,
    val url: String
)