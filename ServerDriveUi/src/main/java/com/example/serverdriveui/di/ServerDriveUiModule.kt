package com.example.serverdriveui.di

import com.example.serverdriveui.SdUiActivity
import com.example.serverdriveui.SdUiRepository
import com.example.serverdriveui.SdUiViewModel
import com.example.serverdriveui.service.SdUiMockService
import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.actions.BackAction
import com.example.serverdriveui.ui.actions.BusinessSuccessAction
import com.example.serverdriveui.ui.actions.CloseAction
import com.example.serverdriveui.ui.actions.ContinueAction
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.actions.manager.ActionManager
import com.example.serverdriveui.ui.actions.manager.ActionParser
import com.example.serverdriveui.ui.components.ButtonComponent
import com.example.serverdriveui.ui.components.ColumnComponent
import com.example.serverdriveui.ui.components.RowComponent
import com.example.serverdriveui.ui.components.SpacerComponent
import com.example.serverdriveui.ui.components.TextComponent
import com.example.serverdriveui.ui.components.TextInputComponent
import com.example.serverdriveui.ui.components.manager.Component
import com.example.serverdriveui.ui.components.manager.ComponentManager
import com.example.serverdriveui.ui.components.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.AllTrueValidator
import com.example.serverdriveui.ui.validator.MinLengthValidator
import com.example.serverdriveui.ui.validator.Validator
import com.example.serverdriveui.ui.validator.ValidatorManager
import com.example.serverdriveui.ui.validator.ValidatorParser
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ServerDriveUiModule = module {
    scope<SdUiActivity> {
        //single<SdUiService> { get<Retrofit>().create(SdUiService::class.java) }
        factory<SdUiService> { SdUiMockService() }
        factory<SdUiRepository> { SdUiRepository(sdUiService = get(), componentParser = get()) }

        //Parsers
        factory<ComponentParser> { ComponentParser(componentManager = get(), actionParser = get(), validatorParser = get()) }
        factory<ActionParser> { ActionParser(actionManager = get()) }
        factory<ValidatorParser> { ValidatorParser(validationManager = get()) }

        //Managers
        scoped<ComponentStateManager> { ComponentStateManager() }
        factory<ComponentManager> { ComponentManager(koinScope = this) } //Corrigir o scope
        factory<ActionManager> { ActionManager(koinScope = this) }
        factory<ValidatorManager> { ValidatorManager(koinScope = this) }

        //Components
        factory<TextComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> TextComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<TextInputComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> TextInputComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<SpacerComponent> { (staticProperties: Map<String, String>, validators: List<Validator>) -> SpacerComponent(staticProperties, validators) }
        factory<RowComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, innerComponents: List<Component>, validators: List<Validator>) -> RowComponent(dynamicProperties, staticProperties, innerComponents, validators) }
        factory<ColumnComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, innerComponents: List<Component>, validators: List<Validator>) -> ColumnComponent(dynamicProperties, staticProperties, innerComponents, validators) }
        factory<ButtonComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) -> ButtonComponent(dynamicProperties, staticProperties, action, get(), validators) }

        //Actions
        factory<CloseAction> { (data: Map<String, String>) -> CloseAction(data) }
        factory<ContinueAction> { (data: Map<String, String>) -> ContinueAction(data, get()) }
        factory<BackAction> { (data: Map<String, String>) -> BackAction(data) }
        factory<BusinessSuccessAction> { (data: Map<String, String>) -> BusinessSuccessAction(data) }

        //Validators
        factory<MinLengthValidator> { (model: ValidatorModel) -> MinLengthValidator(model, get()) }
        factory<AllTrueValidator> { (model: ValidatorModel) -> AllTrueValidator(model, get()) }

        //UI
        viewModel { (screenId: String, screenData: String) -> SdUiViewModel(screenId = screenId, screenData = screenData, repository = get()) }
    }
}