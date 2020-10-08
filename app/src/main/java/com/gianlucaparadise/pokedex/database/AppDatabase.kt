package com.gianlucaparadise.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gianlucaparadise.pokedex.vo.PokemonListItem

@Database(entities = [PokemonListItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonListItemDao(): PokemonListItemDao
}