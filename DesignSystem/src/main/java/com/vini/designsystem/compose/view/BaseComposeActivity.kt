package com.vini.designsystem.compose.view

import androidx.activity.ComponentActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

open class BaseComposeActivity : ComponentActivity(), AndroidScopeComponent {
    override val scope: Scope by activityScope()
}