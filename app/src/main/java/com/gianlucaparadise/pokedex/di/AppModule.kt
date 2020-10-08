package com.gianlucaparadise.pokedex.di

import com.gianlucaparadise.pokedex.repository.PokedexRepository
import com.gianlucaparadise.pokedex.repository.PokedexRepositoryImpl
import com.gianlucaparadise.pokedex.ui.detail.DetailViewModel
import com.gianlucaparadise.pokedex.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<PokedexRepository> { PokedexRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel() }
}