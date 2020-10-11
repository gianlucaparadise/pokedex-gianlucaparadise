package com.gianlucaparadise.pokedex.database

import androidx.room.TypeConverter
import com.gianlucaparadise.pokedex.vo.Stats
import com.gianlucaparadise.pokedex.vo.Type
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DatabaseTypeConverters {

    private val moshi = Moshi.Builder().build()

    // I'm using Type Converters for Lists in order to avoid Foreign keys and multiple tables

    //region List Of Stats
    private val listOfStats = Types.newParameterizedType(List::class.java, Stats::class.java)
    private val jsonAdapterListOfStats: JsonAdapter<List<Stats>> = moshi.adapter(listOfStats)

    @TypeConverter
    fun listOfStatsToJson(listOfStats: List<Stats>?): String? {
        return jsonAdapterListOfStats.toJson(listOfStats)
    }

    @TypeConverter
    fun jsonToListOfStats(json: String?): List<Stats>? {
        return json?.let { jsonAdapterListOfStats.fromJson(json) }
    }
    //endregion

    //region List Of Type
    private val listOfTypes = Types.newParameterizedType(List::class.java, Type::class.java)
    private val jsonAdapterListOfTypes: JsonAdapter<List<Type>> = moshi.adapter(listOfTypes)

    @TypeConverter
    fun listOfTypeToJson(listOfTypes: List<Type>?): String? {
        return jsonAdapterListOfTypes.toJson(listOfTypes)
    }

    @TypeConverter
    fun jsonToListOfTypes(json: String?): List<Type>? {
        return json?.let { jsonAdapterListOfTypes.fromJson(json) }
    }
    //endregion
}