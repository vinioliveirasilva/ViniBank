package com.vini.common.mvvm

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SavedPropertyDelegate<T>(
    private val state: SavedStateHandle,
    private var stateKey: String,
    initialValue: T,
) : ReadWriteProperty<Any, T> {

    private var refValue = initialValue
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return refValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        refValue = value
        state[stateKey] = value
    }
}