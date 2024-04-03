package com.vini.designsystem.view

import androidx.annotation.LayoutRes
import org.koin.androidx.scope.ScopeFragment

open class BaseFragment(
    @LayoutRes val layoutRes: Int,
) : ScopeFragment(layoutRes)