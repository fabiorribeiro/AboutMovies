package br.com.dev.aboutmovies.utilities

import androidx.room.TypeConverter
import br.com.dev.sobrefilmes.models.Link
import br.com.dev.sobrefilmes.models.MultiMedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun Link_to_Json(value: Link?): String {
        val gson = Gson()
        val type = object : TypeToken<Link>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun json_to_Link(value: String): Link? {
        val gson = Gson()
        val type = object : TypeToken<Link>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun MultiMedia_to_Json(value: MultiMedia?): String {
        val gson = Gson()
        val type = object : TypeToken<MultiMedia>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun json_to_MultiMedia(value: String): MultiMedia? {
        val gson = Gson()
        val type = object : TypeToken<MultiMedia>() {}.type
        return gson.fromJson(value, type)
    }
}