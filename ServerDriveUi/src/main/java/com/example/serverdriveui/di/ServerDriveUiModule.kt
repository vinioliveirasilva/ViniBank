package com.example.serverdriveui.di

import com.example.serverdriveui.GlobalStateManager
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
import com.example.serverdriveui.ui.action.actions.NavigateAction
import com.example.serverdriveui.ui.action.actions.ToBooleanAction
import com.example.serverdriveui.ui.action.actions.ToIntAction
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BottomSheetComponent
import com.example.serverdriveui.ui.component.components.BoxComponent
import com.example.serverdriveui.ui.component.components.CardComponent
import com.example.serverdriveui.ui.component.components.ColumnComponent
import com.example.serverdriveui.ui.component.components.LazyColumnComponent
import com.example.serverdriveui.ui.component.components.LazyRowComponent
import com.example.serverdriveui.ui.component.components.LottieAnimationComponent
import com.example.serverdriveui.ui.component.components.RowComponent
import com.example.serverdriveui.ui.component.components.SnackBarComponent
import com.example.serverdriveui.ui.component.components.SpacerComponent
import com.example.serverdriveui.ui.component.components.TextComponent
import com.example.serverdriveui.ui.component.components.TopAppBarComponent
import com.example.serverdriveui.ui.component.components.button.ButtonComponent
import com.example.serverdriveui.ui.component.components.button.ElevatedButtonComponent
import com.example.serverdriveui.ui.component.components.button.IconButtonComponent
import com.example.serverdriveui.ui.component.components.button.OutlinedButtonComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordViewModel
import com.example.serverdriveui.ui.component.components.dialog.DialogComponent
import com.example.serverdriveui.ui.component.components.divider.HorizontalDividerComponent
import com.example.serverdriveui.ui.component.components.divider.VerticalDividerComponent
import com.example.serverdriveui.ui.component.components.icon.IconComponent
import com.example.serverdriveui.ui.component.components.icon.ImageComponent
import com.example.serverdriveui.ui.component.components.map.GoogleMapsComponent
import com.example.serverdriveui.ui.component.components.navigationbar.NavigationBarComponent
import com.example.serverdriveui.ui.component.components.navigationbar.NavigationBarItemComponent
import com.example.serverdriveui.ui.component.components.pager.HorizontalPagerComponent
import com.example.serverdriveui.ui.component.components.sdui.SdUiComponent
import com.example.serverdriveui.ui.component.components.sdui.SdUiComponentViewModel
import com.example.serverdriveui.ui.component.components.textinput.OutlinedTextInputComponent
import com.example.serverdriveui.ui.component.components.textinput.TextInputComponent
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.ui.validator.validators.AllTrueValidator
import com.example.serverdriveui.ui.validator.validators.EmailValidator
import com.example.serverdriveui.ui.validator.validators.IntToDynamicComponentValidator
import com.example.serverdriveui.ui.validator.validators.IntToStringValidator
import com.example.serverdriveui.ui.validator.validators.MinLengthValidator
import com.google.gson.JsonObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val ServerDriveUiComponents = module {
    factory<Component>(
        named(TopAppBarComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        TopAppBarComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(SpacerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        SpacerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get()
        )
    }

    factory<Component>(
        named(DialogComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        DialogComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
        )
    }

    factory<Component>(
        named(BottomSheetComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        BottomSheetComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
        )
    }

    factory<Component>(
        named(TextComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        TextComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get()
        )
    }

    factory<Component>(
        named(OutlinedTextInputComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        OutlinedTextInputComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get()
        )
    }

    factory<Component>(
        named(ColumnComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        ColumnComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(RowComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        RowComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get(),
        )
    }

    factory<Component>(
        named(BoxComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        BoxComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get(),
        )
    }

    factory<Component>(
        named(LazyColumnComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        LazyColumnComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(LazyRowComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        LazyRowComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(HorizontalPagerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        HorizontalPagerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get()
        )
    }

    factory<Component>(
        named(ButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        ButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(CardComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        CardComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(OutlinedButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        OutlinedButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(ElevatedButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        ElevatedButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(LottieAnimationComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        LottieAnimationComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            actionParser = get()
        )
    }

    factory<Component>(
        named(TextInputComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        TextInputComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(CreatePasswordComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        CreatePasswordComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            viewModel = get()
        )
    }

    viewModelOf(::CreatePasswordViewModel)


    factory<Component>(
        named(SdUiComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        val internalStateManager: ComponentStateManager = get()
        SdUiComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            viewModel = get { parametersOf(internalStateManager) }
        )
    }

    viewModel { (componentStateManager: ComponentStateManager) ->
        SdUiComponentViewModel(
            repository = get(),
            componentParser = get(),
            componentStateManager = componentStateManager
        )
    }

    factory<Component>(
        named(NavigationBarComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        NavigationBarComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
        )
    }

    factory<Component>(
        named(NavigationBarItemComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        NavigationBarItemComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
        )
    }

    factory<Component>(
        named(HorizontalDividerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        HorizontalDividerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(HorizontalDividerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        HorizontalDividerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(VerticalDividerComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        VerticalDividerComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(IconComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        IconComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(ImageComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        ImageComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }

    factory<Component>(
        named(IconButtonComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        IconButtonComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get(),
        )
    }

    factory<Component>(
        named(SnackBarComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        SnackBarComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
            componentParser = get(),
            actionParser = get(),
        )
    }

    factory<Component>(
        named(GoogleMapsComponent.IDENTIFIER)
    ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>, componentStateManager: ComponentStateManager) ->
        GoogleMapsComponent(
            model = jsonComponent,
            properties = properties,
            stateManager = componentStateManager,
            validatorParser = get(),
        )
    }
}

val ServerDriveUiModule = module {

    single<GlobalStateManager> { GlobalStateManager() }
    factory<ComponentStateManager> { ComponentStateManager() }

    //Parsers
    factory<ComponentParser> { ComponentParser(koinScope = this) }
    factory<ActionParser> { ActionParser(koinScope = this) }
    factory<ValidatorParser> { ValidatorParser(koinScope = this) }

    single<SdUiRepository> { SdUiRepository(sdUiService = get()) }
    single<SdUiService> { get<Retrofit>().create(SdUiService::class.java) }

    //UIs
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

val ServerDriveUiValidators = module {
    factory<Validator>(named(MinLengthValidator.IDENTIFIER)) { (model: ValidatorModel, componentStateManager: ComponentStateManager) ->
        MinLengthValidator(
            model = model,
            componentStateManager = componentStateManager
        )
    }
    factory<Validator>(named(AllTrueValidator.IDENTIFIER)) { (model: ValidatorModel, componentStateManager: ComponentStateManager) ->
        AllTrueValidator(
            model = model,
            componentStateManager = componentStateManager
        )
    }
    factory<Validator>(named(EmailValidator.IDENTIFIER)) { (model: ValidatorModel, componentStateManager: ComponentStateManager) ->
        EmailValidator(
            model = model,
            componentStateManager = componentStateManager
        )
    }
    factory<Validator>(named(IntToStringValidator.IDENTIFIER)) { (model: ValidatorModel, componentStateManager: ComponentStateManager) ->
        IntToStringValidator(
            model = model,
            componentStateManager = componentStateManager
        )
    }
    factory<Validator>(named(IntToDynamicComponentValidator.IDENTIFIER)) { (model: ValidatorModel, componentStateManager: ComponentStateManager) ->
        IntToDynamicComponentValidator(
            model = model,
            componentStateManager = componentStateManager,
            componentParser = get()
        )
    }
}

val ServerDriveUiActions = module {
    factory<Action>(named(CloseAction.IDENTIFIER)) { (data: Map<String, String>, _: ComponentStateManager) ->
        CloseAction(
            data = data
        )
    }
    factory<Action>(named(ContinueAction.IDENTIFIER)) { (data: Map<String, String>, componentStateManager: ComponentStateManager) ->
        ContinueAction(
            data = data,
            stateManager = componentStateManager,
            globalStateManager = get()
        )
    }
    factory<Action>(named(BackAction.IDENTIFIER)) { (data: Map<String, String>, _: ComponentStateManager) ->
        BackAction(
            data = data
        )
    }
    factory<Action>(named(BusinessSuccessAction.IDENTIFIER)) { (data: Map<String, String>, _: ComponentStateManager) ->
        BusinessSuccessAction(
            data = data
        )
    }
    factory<Action>(named(NavigateAction.IDENTIFIER)) { (data: Map<String, String>, componentStateManager: ComponentStateManager) ->
        NavigateAction(
            data = data,
            stateManager = componentStateManager,
            globalStateManager = get()
        )
    }
    factory<Action>(named(ToBooleanAction.IDENTIFIER)) { (data: Map<String, String>, componentStateManager: ComponentStateManager) ->
        ToBooleanAction(
            data = data,
            stateManager = componentStateManager,
        )
    }
    factory<Action>(named(ToIntAction.IDENTIFIER)) { (data: Map<String, String>, componentStateManager: ComponentStateManager) ->
        ToIntAction(
            data = data,
            stateManager = componentStateManager,
        )
    }
}

val ServerDriverUiModules = module {
    includes(
        ServerDriveUiModule,
        ServerDriveUiComponents,
        ServerDriveUiValidators,
        ServerDriveUiActions,
    )
}