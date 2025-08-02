package com.example.serverdriveui.ui.component.components.icon.properties

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Autorenew
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
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.example.serverdriveui.util.JsonUtil.asString

class IconNameComponentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : IconNameComponent,
    BasePropertyData<String?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "icon",
        transformToData = { it?.asString() },
        defaultPropertyValue = null,
    ) {

    override val icon: ImageVector?
        @Composable
        get() = IconLibrary[getValue()]
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
        "Visibility" to Icons.Filled.Visibility,
        "VisibilityOff" to Icons.Filled.VisibilityOff,
        "Autorenew" to Icons.Filled.Autorenew,

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
        "ArrowForward" to Icons.AutoMirrored.Filled.ArrowForward,
        "ArrowBack" to Icons.AutoMirrored.Filled.ArrowBack,
        "Theme" to Icons.Default.DarkMode,
        "Lock" to Icons.Default.Lock,
        "PersonSearch" to Icons.Default.PersonSearch,
        "Receipt" to Icons.Default.Receipt,
        "ReceiptLong" to Icons.AutoMirrored.Filled.ReceiptLong,
        "Warning" to Icons.Outlined.Warning,
        "Money" to Icons.Outlined.AttachMoney,
        "ShoppingBag" to Icons.Filled.ShoppingBag,
        "SupervisorAccount" to Icons.Filled.SupervisorAccount,
    )

interface IconNameComponent {
    @get:Composable
    val icon: ImageVector?
}