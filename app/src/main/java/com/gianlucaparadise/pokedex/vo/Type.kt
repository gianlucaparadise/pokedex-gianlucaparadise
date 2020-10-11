package com.gianlucaparadise.pokedex.vo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Type(val slot: Int, val type: NameUrlPair)