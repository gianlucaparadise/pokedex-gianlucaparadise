package com.gianlucaparadise.pokedex.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem

@Dao
interface PokemonListItemDao {
    @Query("SELECT * FROM PokemonListItem")
    fun getAll(): DataSource.Factory<Int, PokemonListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: List<PokemonListItem>)
}