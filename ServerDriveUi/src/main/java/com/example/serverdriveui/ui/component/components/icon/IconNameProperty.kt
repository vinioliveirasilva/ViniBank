package com.example.serverdriveui.ui.component.components.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue

class IconNameProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : IconNameComponent,
    BasePropertyData<ImageVector>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "icon",
        propertyValueTransformation = { IconLibrary[it] },
        defaultPropertyValue = ImageVector.Builder("", 0.dp, 0.dp, 0f, 0f).build()
    ) {

    override val icon: ImageVector
        @Composable
        get() = getValue().asValue()
}

private val IconLibrary = mapOf(
    "Home" to Icons.Default.Home,
    "HomeOutline" to Icons.Outlined.Home,
    "Payment" to Icons.Filled.Payment,
    "PaymentOutline" to Icons.Outlined.Payment,
    "Investment" to Icons.Filled.BarChart,
    "InvestmentOutline" to Icons.Outlined.BarChart,
    "User" to Icons.Default.AccountCircle,
    "Logout" to Icons.AutoMirrored.Filled.Logout,
    "LeftArrow" to Icons.Default.ArrowBackIosNew,
    "RightArrow" to Icons.AutoMirrored.Filled.ArrowForwardIos,
)