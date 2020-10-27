package com.gianlucaparadise.pokedex.vo.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListItemWeb(
    val name: String,
    val url: String
) {
    val id: Int = url.trim('/').split('/').last().toInt()
}