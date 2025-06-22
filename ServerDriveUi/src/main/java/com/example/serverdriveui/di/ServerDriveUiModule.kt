package com.example.serverdriveui.di

import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.SdUiActivity
import com.example.serverdriveui.SdUiActivityViewModel
import com.example.serverdriveui.SdUiRepository
import com.example.serverdriveui.SdUiViewModel
import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.action.actions.BackAction
import com.example.serverdriveui.ui.action.actions.BusinessSuccessAction
import com.example.serverdriveui.ui.action.actions.CloseAction
import com.example.serverdriveui.ui.action.actions.ContinueAction
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.ButtonComponent
import com.example.serverdriveui.ui.component.components.ColumnComponent
import com.example.serverdriveui.ui.component.components.ElevatedButtonComponent
import com.example.serverdriveui.ui.component.components.LazyColumnComponent
import com.example.serverdriveui.ui.component.components.LottieAnimationComponent
import com.example.serverdriveui.ui.component.components.OutlinedButtonComponent
import com.example.serverdriveui.ui.component.components.RowComponent
import com.example.serverdriveui.ui.component.components.SpacerComponent
import com.example.serverdriveui.ui.component.components.TextComponent
import com.example.serverdriveui.ui.component.components.TopAppBarComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordViewModel
import com.example.serverdriveui.ui.component.components.textinput.OutlinedTextInputComponent
import com.example.serverdriveui.ui.component.components.textinput.TextInputComponent
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.AllTrueValidator
import com.example.serverdriveui.ui.validator.EmailValidator
import com.example.serverdriveui.ui.validator.MinLengthValidator
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val ServerDriveUiComponents = module {
    factory<Component>(
        named(TopAppBarComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        TopAppBarComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(SpacerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        SpacerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get()
        )
    }

    factory<Component>(
        named(TextComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        TextComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get()
        )
    }

    factory<Component>(
        named(OutlinedTextInputComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        OutlinedTextInputComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get()
        )
    }

    factory<Component>(
        named(ColumnComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        ColumnComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(RowComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        RowComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(LazyColumnComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        LazyColumnComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(ButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        ButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(OutlinedButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        OutlinedButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(ElevatedButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        ElevatedButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(LottieAnimationComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        LottieAnimationComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(TextInputComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        TextInputComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(CreatePasswordComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
        CreatePasswordComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = get(),
            validatorParser = get(),
            viewModel = get()
        )
    }

    viewModelOf(::CreatePasswordViewModel)
}

val ServerDriveUiModule = module {

    single<GlobalStateManager> { GlobalStateManager() }
    single<ComponentStateManager> { ComponentStateManager() }

    //Parsers
    factory<ComponentParser> { ComponentParser(koinScope = this) }
    factory<ActionParser> { ActionParser(koinScope = this) }
    factory<ValidatorParser> { ValidatorParser(koinScope = this) }

    //Validators
    factory<Validator>(named(MinLengthValidator.IDENTIFIER)) { (model: ValidatorModel) ->
        MinLengthValidator(
            model = model,
            componentStateManager = get()
        )
    }
    factory<Validator>(named(AllTrueValidator.IDENTIFIER)) { (model: ValidatorModel) ->
        AllTrueValidator(
            model = model,
            componentStateManager = get()
        )
    }
    factory<Validator>(named(EmailValidator.IDENTIFIER)) { (model: ValidatorModel) ->
        EmailValidator(
            model = model,
            componentStateManager = get()
        )
    }

    //Actions
    factory<Action>(named(CloseAction.IDENTIFIER)) { (data: Map<String, String>) ->
        CloseAction(
            data = data
        )
    }
    factory<Action>(named(ContinueAction.IDENTIFIER)) { (data: Map<String, String>) ->
        ContinueAction(
            data = data,
            stateManager = get(),
            globalStateManager = get()
        )
    }
    factory<Action>(named(BackAction.IDENTIFIER)) { (data: Map<String, String>) ->
        BackAction(
            data = data
        )
    }
    factory<Action>(named(BusinessSuccessAction.IDENTIFIER)) { (data: Map<String, String>) ->
        BusinessSuccessAction(
            data = data
        )
    }

    scope<SdUiActivity> {
        scoped<SdUiService> { get<Retrofit>().create(SdUiService::class.java) }
        factory<SdUiRepository> { SdUiRepository(sdUiService = get()) }

        //UI
        viewModel { (flowId: String, screenData: String) ->
            SdUiActivityViewModel(
                flowId = flowId,
                screenData = screenData,
                repository = get(),
                globalStateManager = get(),
            )
        }
        viewModel { (jsonModel: String) ->
            SdUiViewModel(
                jsonModel = jsonModel,
                repository = get(),
                componentParser = get(),
                globalStateManager = get(),
                componentStateManager = get()
            )
        }
    }
}