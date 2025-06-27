package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vini.common.or
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ComponentParser(
    private val koinScope: Scope
) {

    fun parse(
        data: JsonObject,
        componentTag: String = "components",
        componentStateManager: ComponentStateManager
    ): List<Component> {
        return data.getAsJsonArray(componentTag).or(emptyList()).map {
            val properties = it.asJsonObject.getAsJsonArray("properties").map {
                Gson().fromJson(it, PropertyModel::class.java)
            }.associateBy { it.name }
            val componentType = it.asJsonObject.get("type").asString

            koinScope.getOrNull<Component>(named(componentType)) {
                parametersOf(
                    it,
                    properties,
                    componentStateManager
                )
            } ?: unknownComponent()
        }
    }

    private fun unknownComponent() = object : Component {
        @Composable
        override fun getComponent(navController: NavHostController): @Composable (ColumnScope.() -> Unit) =
            {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red)
                        .padding(5.dp)
                        .background(Color.White)
                ) {
                    Text("Componente desconhecido")
                }
            }
    }
}