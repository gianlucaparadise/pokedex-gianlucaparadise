package com.gianlucaparadise.pokedex.vo.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeWeb(val slot: Int, val type: NameUrlPair)