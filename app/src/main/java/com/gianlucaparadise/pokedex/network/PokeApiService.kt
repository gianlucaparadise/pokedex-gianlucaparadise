package com.gianlucaparadise.pokedex.network

import com.gianlucaparadise.pokedex.vo.PaginatedResponse
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PaginatedResponse<PokemonListItem>
}