package com.vini.common

infix fun <T> T?.or(other: T): T = this ?: other