package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.properties.LottieAnimationDataComponentProperty
import com.example.serverdriveui.ui.component.properties.LottieAnimationDataProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.serialization.json.JsonObject

data class LottieAnimationComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    LottieAnimationDataComponentProperty by LottieAnimationDataProperty(
        properties,
        stateManager,
    ) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.JsonString(getLottieAnimationStringData())
        )

        val progress by animateLottieCompositionAsState(
            composition,
            isPlaying = true,
            restartOnPlay = true,
        )

        LottieAnimation(
            modifier = modifier.fillMaxSize(),
            composition = composition,
            progress = { progress },
        )

        if (progress == ANIMATION_FINISHED) {
            actions["OnClick"]?.execute(navController)
        }
    }

    companion object {
        const val IDENTIFIER = "lottie"
        private const val ANIMATION_FINISHED = 1.0f
    }
}