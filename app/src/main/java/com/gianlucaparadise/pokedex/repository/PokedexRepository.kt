package com.gianlucaparadise.pokedex.repository

import androidx.paging.*
import com.gianlucaparadise.pokedex.database.AppDatabase
import com.gianlucaparadise.pokedex.mapper.toPokemon
import com.gianlucaparadise.pokedex.network.PokeApiService
import com.gianlucaparadise.pokedex.vo.main.Pokemon
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getPokemonList(): Flow<PagingData<PokemonListItem>>
    suspend fun getPokemon(name: String): Pokemon
}

class PokedexRepositoryImpl(
    private val backend: PokeApiService,
    private val database: AppDatabase
) : PokedexRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30
    )

    override fun getPokemonList() = Pager(
        config = pagingConfig,
        remoteMediator = PageKeyedRemoteMediator(database, backend)
    ) {
        database.pokemonListItemDao().getAll()
    }.flow

    override suspend fun getPokemon(name: String): Pokemon {

        val dbPokemon = database.pokemonDetailDao().getByName(name)
        if (dbPokemon != null) return dbPokemon

        val webPokemon = backend.getPokemon(name)
        val pokemon = webPokemon.toPokemon()
        database.pokemonDetailDao().insert(pokemon)

        return pokemon
    }
}