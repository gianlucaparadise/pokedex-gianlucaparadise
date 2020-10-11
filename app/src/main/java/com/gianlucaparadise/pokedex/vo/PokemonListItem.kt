package com.gianlucaparadise.pokedex.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Entity
@JsonClass(generateAdapter = true)
data class PokemonListItem(
    @PrimaryKey val name: String,
    val url: String
) : Serializable {
    @Ignore
    val id: Int = url.trim('/').split('/').last().toInt()
}