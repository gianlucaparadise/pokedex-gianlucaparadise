package com.gianlucaparadise.pokedex.network

import com.gianlucaparadise.pokedex.vo.network.PaginatedResponse
import com.gianlucaparadise.pokedex.vo.network.PokemonWeb
import com.gianlucaparadise.pokedex.vo.network.PokemonListItemWeb
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PaginatedResponse<PokemonListItemWeb>

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonWeb
}