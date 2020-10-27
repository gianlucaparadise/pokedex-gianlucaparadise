package com.gianlucaparadise.pokedex.database

import androidx.room.TypeConverter
import com.gianlucaparadise.pokedex.vo.main.StatsDescriptor
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.inject

class DatabaseTypeConverters : KoinComponent {

    private val moshi: Moshi by inject()

    // I'm using Type Converters for Lists in order to avoid Foreign keys and multiple tables

    //region List Of Strings
    private val listOfStrings = Types.newParameterizedType(List::class.java, String::class.java)
    private val jsonAdapterListOfStrings: JsonAdapter<List<String>> = moshi.adapter(listOfStrings)

    @TypeConverter
    fun listOfStringsToJson(listOfStrings: List<String>?): String? {
        return jsonAdapterListOfStrings.toJson(listOfStrings)
    }

    @TypeConverter
    fun jsonToListOfStrings(json: String?): List<String>? {
        return json?.let { jsonAdapterListOfStrings.fromJson(json) }
    }
    //endregion

    //region StatsDescriptor
    private val jsonAdapterStatsDescriptor: JsonAdapter<StatsDescriptor> = moshi.adapter(StatsDescriptor::class.java)

    @TypeConverter
    fun statsDescriptorToJson(statsDescriptor: StatsDescriptor?): String? {
        return jsonAdapterStatsDescriptor.toJson(statsDescriptor)
    }

    @TypeConverter
    fun jsonToStatsDescriptor(json: String?): StatsDescriptor? {
        return json?.let { jsonAdapterStatsDescriptor.fromJson(json) }
    }
    //endregion
}