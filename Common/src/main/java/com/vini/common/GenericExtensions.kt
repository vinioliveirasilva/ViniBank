package com.vini.common

infix fun <T> T?.or(other: T): T = this ?: other

fun <T> String?.runWhen(isNull: () -> T, notNull: (String) -> T) : T {
    return if(this.isNullOrBlank()) {
        isNull()
    } else {
        notNull(this)
    }
}