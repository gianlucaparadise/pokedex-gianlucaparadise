package com.gianlucaparadise.pokedex.vo.main

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PokemonListItem(
    @PrimaryKey val id: Int,
    val name: String,
) : Serializable