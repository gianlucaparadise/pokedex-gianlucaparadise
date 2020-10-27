package com.gianlucaparadise.pokedex.mapper

import com.gianlucaparadise.pokedex.vo.main.StatsDescriptor
import com.gianlucaparadise.pokedex.vo.network.StatsWeb
import java.util.*

fun List<StatsWeb>.toStatsDescriptor(): StatsDescriptor {
    var hp: StatsDescriptor.Stat? = null
    var attack: StatsDescriptor.Stat? = null
    var defense: StatsDescriptor.Stat? = null
    var specialAttack: StatsDescriptor.Stat? = null
    var specialDefense: StatsDescriptor.Stat? = null
    var speed: StatsDescriptor.Stat? = null
    var accuracy: StatsDescriptor.Stat? = null
    var evasion: StatsDescriptor.Stat? = null

    for (rawStat in this) {
        val tempStat = StatsDescriptor.Stat(rawStat.base_stat, rawStat.effort)
        when (rawStat.stat.name.toLowerCase(Locale.getDefault())) {
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
