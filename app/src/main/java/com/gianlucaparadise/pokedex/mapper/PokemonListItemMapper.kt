package com.gianlucaparadise.pokedex.mapper

import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import com.gianlucaparadise.pokedex.vo.network.PokemonListItemWeb

fun PokemonListItemWeb.toPokemonListItem(): PokemonListItem {
    return PokemonListItem(
        id = this.id,
        name = this.name
    )
}

fun List<PokemonListItemWeb>.toPokemonListItemList(): List<PokemonListItem> {
    return this.map { p -> p.toPokemonListItem() }
}