package com.example.serverdriveui.ui.component.components.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.pager.properties.ContentPaddingComponentProperty
import com.example.serverdriveui.ui.component.components.pager.properties.ContentPaddingProperty
import com.example.serverdriveui.ui.component.components.pager.properties.CurrentPageComponentProperty
import com.example.serverdriveui.ui.component.components.pager.properties.CurrentPageProperty
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.VerticalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalAlignmentProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.serialization.json.JsonObject

class HorizontalPagerComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    VerticalAlignmentComponentProperty by VerticalAlignmentProperty(properties, stateManager),
    ContentPaddingComponentProperty by ContentPaddingProperty(properties, stateManager),
    CurrentPageComponentProperty by CurrentPageProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {

        val components = componentParser.parseList(data = model)
        val currentPage = getCurrentPage()
        val pagerState = rememberPagerState(
            pageCount = { components.size },
            initialPage = currentPage
        )

        LaunchedEffect(pagerState.currentPage) { setCurrentPage(pagerState.currentPage) }
        LaunchedEffect(currentPage) { pagerState.animateScrollToPage(currentPage) }

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(getContentPadding().dp)
        ) { page -> components[page].getComponent(navController).invoke() }
    }

    companion object {
        const val IDENTIFIER = "horizontalPager"
    }
}