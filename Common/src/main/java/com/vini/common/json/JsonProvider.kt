package com.vini.common.json

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class JsonProvider(val provider: Json = Json {}) {
    inline fun <reified T> toJson(toSerialize: T): String {
        return provider.encodeToString(toSerialize)
    }

    inline fun <reified T> fromJson(json: String): T {
        return provider.decodeFromString<T>(json)
    }
}