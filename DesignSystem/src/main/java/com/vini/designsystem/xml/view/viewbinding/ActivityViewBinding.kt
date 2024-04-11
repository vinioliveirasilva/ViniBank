@file:Suppress("UNCHECKED_CAST")

package com.vini.designsystem.xml.view.viewbinding

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding() = ActivityViewBindingDelegate(
    T::class.java,
    this
)

class ActivityViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    view: AppCompatActivity
) : ReadOnlyProperty<AppCompatActivity, T> {

    private var binding: T? = null
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        view.lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    binding = null
                }
            }
        )
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T =
        binding ?: thisRef.rootView()?.let { view ->
            (bindMethod.invoke(null, view) as T).also { binding = it }
        }
        ?: error("Cannot access view bindings when view is null (before onCreateView() or after onDestroyView()).")

    private fun AppCompatActivity.rootView() = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

}
