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
import com.example.serverdriveui.service.model.ComponentModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ComponentParser(
    private val koinScope: Scope
) {

    fun parse(data: JsonObject, componentTag: String = "components"): List<Component> {
        val componentType = object : TypeToken<List<ComponentModel>>() {}.type
        val rawComponent = data.getAsJsonArray(componentTag) ?: return emptyList()
        val components: List<ComponentModel> = Gson().fromJson(rawComponent, componentType)
        return components.mapIndexed { index, component ->
            koinScope.getOrNull<Component>(named(component.type)) {
                parametersOf(
                    rawComponent[index],
                    component.properties.associateBy { it.name }
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