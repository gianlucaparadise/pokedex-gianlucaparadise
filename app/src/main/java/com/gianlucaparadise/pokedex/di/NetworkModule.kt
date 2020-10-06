package com.gianlucaparadise.pokedex.di

import com.gianlucaparadise.pokedex.network.PokeApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory { providePokeApiService(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun providePokeApiService(retrofit: Retrofit): PokeApiService {
    return retrofit.create(PokeApiService::class.java)
}