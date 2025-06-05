package com.vini.common.gson

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonProvider(private val gson: Gson = Gson()) {
    fun <T> toJson(toSerialize: T) : String {
        return gson.toJson(toSerialize)
    }

    fun <T> fromJson(json: String, clazz: Class<T>) : T {
        return gson.fromJson(json, clazz)
    }

    fun <T> fromTypedJson(json: String, typeOfT: Type) : T {
        return gson.fromJson<T>(json, typeOfT)
    }
}