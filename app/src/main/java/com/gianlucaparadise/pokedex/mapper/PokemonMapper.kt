package com.gianlucaparadise.pokedex.mapper

import com.gianlucaparadise.pokedex.vo.main.Pokemon
import com.gianlucaparadise.pokedex.vo.network.PokemonWeb

fun PokemonWeb.toPokemon() : Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        sprites = this.sprites?.toSprites(),
        stats = this.stats?.toStatsDescriptor(),
        types = this.types?.toTypeList()
    )
}

fun List<PokemonWeb>.toPokemonList(): List<Pokemon> {
    return this.map { p -> p.toPokemon() }
}