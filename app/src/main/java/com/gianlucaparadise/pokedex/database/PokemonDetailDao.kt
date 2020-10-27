package com.gianlucaparadise.pokedex.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gianlucaparadise.pokedex.vo.main.Pokemon

@Dao
interface PokemonDetailDao {
    @Query("SELECT * FROM Pokemon WHERE name = :name")
    suspend fun getByName(name: String): Pokemon?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)
}