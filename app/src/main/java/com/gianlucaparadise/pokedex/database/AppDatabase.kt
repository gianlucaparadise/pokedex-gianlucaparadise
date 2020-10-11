package com.gianlucaparadise.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gianlucaparadise.pokedex.vo.Pokemon
import com.gianlucaparadise.pokedex.vo.PokemonListItem

@Database(entities = [PokemonListItem::class, Pokemon::class], version = 1)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonListItemDao(): PokemonListItemDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
}