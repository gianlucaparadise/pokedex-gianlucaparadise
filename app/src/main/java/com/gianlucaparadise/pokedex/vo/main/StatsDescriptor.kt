package com.gianlucaparadise.pokedex.vo.main

import com.squareup.moshi.JsonClass

/**
 * This class helps parsing the raw backend Stats data
 */
@JsonClass(generateAdapter = true)
data class StatsDescriptor(
    val hp: Stat?,
    val attack: Stat?,
    val defense: Stat?,
    val specialAttack: Stat?,
    val specialDefense: Stat?,
    val speed: Stat?,
    val accuracy: Stat?,
    val evasion: Stat?
) {
    @JsonClass(generateAdapter = true)
    data class Stat(val base: Int, val effort: Int)
}