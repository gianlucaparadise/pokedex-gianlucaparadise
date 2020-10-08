package com.gianlucaparadise.pokedex.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class PokemonListItem(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "url") val url: String
) {
    @Ignore
    val id: Int = url.trim('/').split('/').last().toInt()
}