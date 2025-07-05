package com.example.serverdriveui.ui.component.components.icon.properties

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue

class IconNameProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : IconNameComponent,
    BasePropertyData<ImageVector?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "icon",
        propertyValueTransformation = { IconLibrary[it] },
        defaultPropertyValue = null
    ) {

    override val icon: ImageVector?
        @Composable
        get() = getValue().asValue()
}

private val IconLibrary: Map<String, ImageVector>
    get() = mapOf(
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

        //TODO Revisar
        "Add" to Icons.Default.Add,
        "Delete" to Icons.Default.Delete,
        "Edit" to Icons.Default.Edit,
        "Favorite" to Icons.Default.Favorite,
        "Search" to Icons.Default.Search,
        "Settings" to Icons.Default.Settings,
        "Info" to Icons.Default.Info,
        "Close" to Icons.Default.Close,
        "Check" to Icons.Default.Check,
        "Menu" to Icons.Default.Menu,
        "MoreVert" to Icons.Default.MoreVert,
        "ArrowDropDown" to Icons.Default.ArrowDropDown,
        "ArrowDropUp" to Icons.Default.ArrowDropUp,
        "ArrowForward" to Icons.Default.ArrowForward,
        "ArrowBack" to Icons.Default.ArrowBack,
        "Theme" to Icons.Default.DarkMode,
        "Lock" to Icons.Default.Lock,
        "PersonSearch" to Icons.Default.PersonSearch,
        "Receipt" to Icons.Default.Receipt,
        "ReceiptLong" to Icons.AutoMirrored.Filled.ReceiptLong,
        "Warning" to Icons.Outlined.Warning,
        "Money" to Icons.Outlined.AttachMoney,
    )