package com.gianlucaparadise.pokedex.vo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Pokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val base_experience: Int?,
    val height: Int?,
    val is_default: Boolean?,
    val order: Int?,
    val weight: Int?,
    val location_area_encounters: String?,
    @Embedded(prefix = "sprites")
    val sprites: Sprites?,
    val stats: List<Stats>?,
    val types: List<Type>?
)