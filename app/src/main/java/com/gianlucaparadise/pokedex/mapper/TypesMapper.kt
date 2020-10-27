package com.gianlucaparadise.pokedex.mapper

import com.gianlucaparadise.pokedex.vo.network.TypeWeb

fun List<TypeWeb>.toTypeList() : List<String>? {
    return this.map { t -> t.type.name }
}