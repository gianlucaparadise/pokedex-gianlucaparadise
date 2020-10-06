package com.gianlucaparadise.pokedex.vo

data class PaginatedResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>?
)