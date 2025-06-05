package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest
import com.example.serverdriveui.ui.components.manager.ComponentParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SdUiRepository(
    private val sdUiService: SdUiService,
    private val componentParser: ComponentParser,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getScreen(screenId: String, screenData: String) = sdUiService
        .getScreenModel(SdUiRequest(screenId, screenData))
        .map {

            //save cache
            componentParser.parse(it.components)
        }.flowOn(dispatcher)

}