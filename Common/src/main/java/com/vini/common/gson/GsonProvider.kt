package com.vini.common.gson

import com.google.gson.Gson

class GsonProvider(private val gson: Gson = Gson()) {
    fun <T> toJson(toSerialize: T) : String {
        return gson.toJson(toSerialize)
    }
}