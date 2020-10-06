package com.gianlucaparadise.pokedex.di

import com.gianlucaparadise.pokedex.ui.detail.DetailViewModel
import com.gianlucaparadise.pokedex.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel() }
}