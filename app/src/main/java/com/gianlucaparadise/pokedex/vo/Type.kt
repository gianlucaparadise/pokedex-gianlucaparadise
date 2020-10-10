package com.gianlucaparadise.pokedex.vo

data class Type(val slot: Int, val type: NameUrlPair) {

    val typeEnum = parseTypeEnum(type)

    private fun parseTypeEnum(type: NameUrlPair): TypeEnum {
        return try {
            TypeEnum.valueOf(type.name)
        } catch (e: IllegalArgumentException) {
            TypeEnum.OTHER
        }
    }
}