package com.vini.designsystemsdui.property

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object BaseComponentProperty {
    open class BooleanComponentProperty(
        private val name: String,
        private val internalValue: Boolean,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }

    open class StringComponentProperty(
        private val name: String,
        private val internalValue: String,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }

    open class NumberComponentProperty(
        private val name: String,
        private val internalValue: Number,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }
}