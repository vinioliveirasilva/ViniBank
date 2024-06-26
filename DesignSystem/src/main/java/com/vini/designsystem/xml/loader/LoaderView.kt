package com.vini.designsystem.xml.loader

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

interface LoaderView {
    fun LifecycleOwner.show(fragmentManager: FragmentManager)
    fun hide()
}