package com.example.serverdriveui.ui.component.components.map

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonObject
import com.google.maps.android.compose.DefaultMapUiSettings
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

data class GoogleMapsComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser
) : BaseComponent(model, properties, stateManager, validatorParser) {

    @Composable
    override fun getInternalComponent(//TODO
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val locationPoint = LatLng(-23.981120, -46.231803)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(locationPoint, 15f)
        }
        GoogleMap(
            modifier = Modifier.size(300.dp).clip(CircleShape),
            cameraPositionState = cameraPositionState,
            uiSettings = DefaultMapUiSettings.copy(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = false,
                scrollGesturesEnabled = false,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
                zoomGesturesEnabled = false,
            )
        ) {
            Marker(
                state = MarkerState(position = locationPoint),
                title = "Localização aproximada"
            )
        }
    }

    companion object {
        const val IDENTIFIER = "googleMaps"
    }
}