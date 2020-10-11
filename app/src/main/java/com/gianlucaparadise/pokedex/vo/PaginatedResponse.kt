package com.gianlucaparadise.pokedex.vo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginatedResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>?
)