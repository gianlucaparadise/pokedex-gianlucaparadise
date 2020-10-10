package com.gianlucaparadise.pokedex.vo

fun createStatsDescriptorFromRawStats(rawStats: List<Stats>?): StatsDescriptor? {
    if (rawStats == null) return null

    var hp: StatsDescriptor.Stat? = null
    var attack: StatsDescriptor.Stat? = null
    var defense: StatsDescriptor.Stat? = null
    var specialAttack: StatsDescriptor.Stat? = null
    var specialDefense: StatsDescriptor.Stat? = null
    var speed: StatsDescriptor.Stat? = null
    var accuracy: StatsDescriptor.Stat? = null
    var evasion: StatsDescriptor.Stat? = null

    for (rawStat in rawStats) {
        val tempStat = StatsDescriptor.Stat(rawStat.base_stat, rawStat.effort)
        when (rawStat.stat.name.toLowerCase()) {
            "hp" -> hp = tempStat
            "attack" -> attack = tempStat
            "defense" -> defense = tempStat
            "special-attack" -> specialAttack = tempStat
            "special-defense" -> specialDefense = tempStat
            "speed" -> speed = tempStat
            "accuracy" -> accuracy = tempStat
            "evasion" -> evasion = tempStat
            else -> {
                // If a new Stat is added to the API, the app needs a new release
            }
        }
    }

    return StatsDescriptor(
        hp, attack, defense, specialAttack, specialDefense, speed, accuracy, evasion
    )
}

/**
 * This class helps parsing the raw backend Stats data
 */
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
    data class Stat(val base: Int, val effort: Int)
}