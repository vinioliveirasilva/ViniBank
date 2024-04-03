package com.vini.designsystem.loader

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner

class LoaderViewComponent : LoaderView {

    private val loader = DefaultLoaderFragment()
    override fun LifecycleOwner.show(fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        loader.show(transaction, "dialog")
    }

    override fun hide() {
        loader.finish()
    }
}