package com.gianlucaparadise.pokedex.di

import com.gianlucaparadise.pokedex.network.PokeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val networkModule = module {
    factory { providePokeApiService(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun providePokeApiService(retrofit: Retrofit): PokeApiService {
    return retrofit.create(PokeApiService::class.java)
}