package com.gianlucaparadise.pokedex.repository

import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.vo.PaginatedResponse
import com.gianlucaparadise.pokedex.vo.PokemonListItem

interface PokedexRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): PaginatedResponse<PokemonListItem>
}

class PokedexRepositoryImpl(val backend: PokeApiService) : PokedexRepository {
    override suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): PaginatedResponse<PokemonListItem> {
        return backend.getPokemonList(offset, limit)
    }

}