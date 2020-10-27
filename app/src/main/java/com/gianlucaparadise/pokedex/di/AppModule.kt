package com.gianlucaparadise.pokedex.di

import android.content.Context
import androidx.room.Room
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.repository.PokedexRepositoryImpl
import com.gianlucaparadise.pokedex.ui.detail.DetailViewModel
import com.gianlucaparadise.pokedex.ui.main.MainViewModel
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import com.squareup.moshi.Moshi
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { provideMoshi() }
    single { provideDatabase(androidContext()) }
    single<PokedexRepository> { PokedexRepositoryImpl(get(), get()) }

    viewModel { MainViewModel(get()) }
    viewModel { (pokemonListItem: PokemonListItem) -> DetailViewModel(pokemonListItem, get()) }
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "pokemon-database").build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
}