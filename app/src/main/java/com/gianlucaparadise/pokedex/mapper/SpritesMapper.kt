package com.gianlucaparadise.pokedex.mapper

import com.gianlucaparadise.pokedex.vo.main.Sprites
import com.gianlucaparadise.pokedex.vo.network.SpritesWeb

fun SpritesWeb.toSprites(): Sprites {
    return Sprites(
        frontDefault = this.front_default
    )
}