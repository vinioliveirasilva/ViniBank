package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.static.LottieAnimationDataComponentProperty
import com.example.serverdriveui.ui.component.properties.static.LottieAnimationDataProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator

data class LottieAnimationComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val staticProperties: Map<String, String>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
    private val validators: List<Validator>,
) : Component,
    LottieAnimationDataComponentProperty by LottieAnimationDataProperty(staticProperties) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val composition by rememberLottieComposition(lottieAnimationSpec)

            val progress by animateLottieCompositionAsState(
                composition,
                isPlaying = true,
                restartOnPlay = true,
            )

            LottieAnimation(
                modifier = Modifier.fillMaxSize(),
                composition = composition,
                progress = { progress },
            )

            if (progress == ANIMATION_FINISHED) {
                action.execute(navController)
            }
        }

    companion object {
        const val IDENTIFIER = "lottie"
        private const val ANIMATION_FINISHED = 1.0f
    }
}