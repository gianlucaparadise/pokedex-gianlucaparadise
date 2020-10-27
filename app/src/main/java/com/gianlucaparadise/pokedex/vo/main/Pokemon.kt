package com.gianlucaparadise.pokedex.vo.main

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val id: Int,
    val name: String,
    @Embedded(prefix = "sprites")
    val sprites: Sprites?,
    val stats: StatsDescriptor?,
    val types: List<String>?
)