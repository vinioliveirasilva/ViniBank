package com.example.serverdriveui.di

import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.SdUiActivity
import com.example.serverdriveui.SdUiActivityViewModel
import com.example.serverdriveui.SdUiModel
import com.example.serverdriveui.SdUiRepository
import com.example.serverdriveui.SdUiViewModel
import com.example.serverdriveui.SdUiViewModel2
import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.actions.BackAction
import com.example.serverdriveui.ui.actions.BusinessSuccessAction
import com.example.serverdriveui.ui.actions.CloseAction
import com.example.serverdriveui.ui.actions.ContinueAction
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.actions.manager.ActionManager
import com.example.serverdriveui.ui.actions.manager.ActionParser
import com.example.serverdriveui.ui.component.components.ButtonComponent
import com.example.serverdriveui.ui.component.components.OutlinedButtonComponent
import com.example.serverdriveui.ui.component.components.ElevatedButtonComponent
import com.example.serverdriveui.ui.component.components.ColumnComponent
import com.example.serverdriveui.ui.component.components.LottieAnimationComponent
import com.example.serverdriveui.ui.component.components.RowComponent
import com.example.serverdriveui.ui.component.components.SpacerComponent
import com.example.serverdriveui.ui.component.components.TextComponent
import com.example.serverdriveui.ui.component.components.TopAppBarComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordViewModel
import com.example.serverdriveui.ui.component.components.textinput.OutlinedTextInputComponent
import com.example.serverdriveui.ui.component.components.textinput.TextInputComponent
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentManager
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.AllTrueValidator
import com.example.serverdriveui.ui.validator.EmailValidator
import com.example.serverdriveui.ui.validator.MinLengthValidator
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.ui.validator.manager.ValidatorManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val ServerDriveUiModule = module {
    scope<SdUiActivity> {
        scoped<SdUiService> { get<Retrofit>().create(SdUiService::class.java) }
        //factory<SdUiService> { SdUiMockService() }
        factory<SdUiRepository> { SdUiRepository(sdUiService = get(), componentParser = get()) }

        //Parsers
        factory<ComponentParser> { ComponentParser(componentManager = get(), actionParser = get(), validatorParser = get()) }
        factory<ActionParser> { ActionParser(actionManager = get()) }
        factory<ValidatorParser> { ValidatorParser(validationManager = get()) }

        //Managers
        scoped<GlobalStateManager> { GlobalStateManager() }
        scoped<ComponentStateManager> { ComponentStateManager() }
        factory<ComponentManager> { ComponentManager(koinScope = this) } //Corrigir o scope
        factory<ActionManager> { ActionManager(koinScope = this) }
        factory<ValidatorManager> { ValidatorManager(koinScope = this) }

        //Components
        factory<TextComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> TextComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<TopAppBarComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> TopAppBarComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<TextInputComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> TextInputComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<OutlinedTextInputComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, validators: List<Validator>) -> OutlinedTextInputComponent(dynamicProperties, staticProperties, get(), validators) }
        factory<SpacerComponent> { (staticProperties: Map<String, String>, validators: List<Validator>) -> SpacerComponent(staticProperties, validators) }
        factory<RowComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, innerComponents: List<Component>, validators: List<Validator>) -> RowComponent(dynamicProperties, staticProperties, innerComponents, validators) }
        factory<ColumnComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, innerComponents: List<Component>, validators: List<Validator>) -> ColumnComponent(dynamicProperties, staticProperties, innerComponents, validators) }
        factory<ButtonComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) -> ButtonComponent(dynamicProperties, staticProperties, action, get(), validators) }
        factory<ElevatedButtonComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) ->
            ElevatedButtonComponent(dynamicProperties, staticProperties, action, get(), validators) }
        factory<OutlinedButtonComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) ->
            OutlinedButtonComponent(dynamicProperties, staticProperties, action, get(), validators) }
        factory<LottieAnimationComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) -> LottieAnimationComponent(dynamicProperties, staticProperties, action, get(), validators) }
        factory<CreatePasswordComponent> { (dynamicProperties: List<PropertyModel>, staticProperties: Map<String, String>, action: Action, validators: List<Validator>) -> CreatePasswordComponent(dynamicProperties, staticProperties, validators, get(), get()) }
        viewModelOf(::CreatePasswordViewModel)

        //Actions
        factory<CloseAction> { (data: Map<String, String>) -> CloseAction(data) }
        factory<ContinueAction> { (data: Map<String, String>) -> ContinueAction(data, get(), get()) }
        factory<BackAction> { (data: Map<String, String>) -> BackAction(data) }
        factory<BusinessSuccessAction> { (data: Map<String, String>) -> BusinessSuccessAction(data) }

        //Validators
        factory<MinLengthValidator> { (model: ValidatorModel) -> MinLengthValidator(model, get()) }
        factory<AllTrueValidator> { (model: ValidatorModel) -> AllTrueValidator(model, get()) }
        factory<EmailValidator> { (model: ValidatorModel) -> EmailValidator(model, get()) }

        //UI
        viewModel { (model: SdUiModel) -> SdUiViewModel(model = model, repository = get(), get()) }
        viewModel { (flowId: String) -> SdUiActivityViewModel(flowId = flowId, repository = get(), get()) }
        viewModel { (screenModel: ScreenModel) -> SdUiViewModel2(model = screenModel, repository = get(), get(), get()) }
    }
}