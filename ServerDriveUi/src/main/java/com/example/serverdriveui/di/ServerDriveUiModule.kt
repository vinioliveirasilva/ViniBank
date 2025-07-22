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
import com.example.serverdriveui.ui.action.actions.MultipleActionsAction
import com.example.serverdriveui.ui.action.actions.NavigateAction
import com.example.serverdriveui.ui.action.actions.ToBooleanAction
import com.example.serverdriveui.ui.action.actions.ToIntAction
import com.example.serverdriveui.ui.action.actions.ToStringAction
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.action.manager.ActionHandler
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BlankComponent
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
import kotlinx.serialization.json.JsonObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit

const val sdUiComponents = "sdUiComponents"
const val sdUiActivity = "sdUiActivity"

fun Koin.getNewScope(name: String, scopeToLink: Scope?): Scope {
    println("criando um novo scopo")
    return getOrCreateScope(name, named(sdUiComponents)).apply {
        scopeToLink?.let { linkTo(it) }
    }
}

fun Koin.getNewScopeActivity(name: String): Scope {
    println("criando um novo activity scopo")
    return getOrCreateScope(name, named(sdUiActivity))
}

val ServerDriveUiComponents = module {
    scope(named(sdUiComponents)) {
        factory<Component>(
            named(TopAppBarComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            TopAppBarComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(SpacerComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            SpacerComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(DialogComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            DialogComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(BottomSheetComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            BottomSheetComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(TextComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            TextComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(BlankComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            BlankComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get()
            )
        }

        factory<Component>(
            named(OutlinedTextInputComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            OutlinedTextInputComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
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
                componentParser = get(),
                actionParser = get()
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
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(BoxComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            BoxComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
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
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(LazyRowComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            LazyRowComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(HorizontalPagerComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            HorizontalPagerComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
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
            named(CardComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            CardComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
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
                actionParser = get(),
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
                viewModel = get(),
                actionParser = get(),
            )
        }

        viewModelOf(::CreatePasswordViewModel)


        factory<Component>(
            named(SdUiComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            SdUiComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                viewModel = get(),
                actionParser = get(),
            )
        }

        viewModel {
            SdUiComponentViewModel(
                repository = get(),
                componentParser = get(),
            )
        }

        factory<Component>(
            named(NavigationBarComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            NavigationBarComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(NavigationBarItemComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            NavigationBarItemComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(HorizontalDividerComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            HorizontalDividerComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(HorizontalDividerComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            HorizontalDividerComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(VerticalDividerComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            VerticalDividerComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(IconComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            IconComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(ImageComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            ImageComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(IconButtonComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            IconButtonComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(SnackBarComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            SnackBarComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                componentParser = get(),
                actionParser = get(),
            )
        }

        factory<Component>(
            named(GoogleMapsComponent.IDENTIFIER)
        ) { (jsonComponent: JsonObject, properties: Map<String, PropertyModel>) ->
            GoogleMapsComponent(
                model = jsonComponent,
                properties = properties,
                stateManager = get(),
                validatorParser = get(),
                actionParser = get(),
            )
        }
    }
}

val ServerDriveUiModule = module {
    scope(named(sdUiActivity)) {
        scoped<ComponentStateManager> { ComponentStateManager() }
        scoped<GlobalStateManager> { GlobalStateManager() }
        viewModel {
            ActionHandler(
                globalStateManager = get(),
                componentStateManager = get(),
                savedStateHandle = get()
            )
        }

        viewModel { (flowId: String, screenData: JsonObject) ->
            SdUiActivityViewModel(
                flowId = flowId,
                screenData = screenData,
                repository = get(),
                closables = listOf(
                    get<GlobalStateManager>(),
                    get<ComponentStateManager>(),
                    AutoCloseable { close() }
                ),
            )
        }
    }

    scope(named(sdUiComponents)) {
        viewModel { (jsonModel: JsonObject) ->
            SdUiViewModel(
                jsonModel = jsonModel,
                repository = get(),
                componentParser = get(),
                globalStateManager = get(),
                componentStateManager = get(),
                closables = listOf(
                    get<ActionParser>(),
                    get<ComponentParser>(),
                    get<ValidatorParser>(),
                    AutoCloseable { close() }
                )
            )
        }

        //Parsers
        scoped<ActionParser> { ActionParser(koinScope = this, componentStateManager = get()) }
        scoped<ComponentParser> { ComponentParser(koinScope = this) }
        scoped<ValidatorParser> { ValidatorParser(koinScope = this) }
    }

    single<SdUiRepository> { SdUiRepository(sdUiService = get()) }
    single<SdUiService> { get<Retrofit>().create(SdUiService::class.java) }
}

val ServerDriveUiValidators = module {
    scope(named(sdUiComponents)) {
        factory<Validator>(named(MinLengthValidator.IDENTIFIER)) { (model: ValidatorModel) ->
            MinLengthValidator(
                model = model,
                componentStateManager = get(),
            )
        }
        factory<Validator>(named(AllTrueValidator.IDENTIFIER)) { (model: ValidatorModel) ->
            AllTrueValidator(
                model = model,
                componentStateManager = get(),
            )
        }
        factory<Validator>(named(EmailValidator.IDENTIFIER)) { (model: ValidatorModel) ->
            EmailValidator(
                model = model,
                componentStateManager = get(),
            )
        }
        factory<Validator>(named(IntToStringValidator.IDENTIFIER)) { (model: ValidatorModel) ->
            IntToStringValidator(
                model = model,
                componentStateManager = get(),
            )
        }
        factory<Validator>(named(IntToDynamicComponentValidator.IDENTIFIER)) { (model: ValidatorModel) ->
            IntToDynamicComponentValidator(
                model = model,
                componentStateManager = get(),
                componentParser = get()
            )
        }
    }
}

val ServerDriveUiActions = module {
    scope(named(sdUiComponents)) {
        factory<Action>(named(CloseAction.IDENTIFIER)) { (data: JsonObject) ->
            CloseAction(
                data = data
            )
        }
        factory<Action>(named(ContinueAction.IDENTIFIER)) { (data: JsonObject) ->
            ContinueAction(
                data = data,
                stateManager = get(),
                globalStateManager = get(),
            )
        }
        factory<Action>(named(BackAction.IDENTIFIER)) { (data: JsonObject) ->
            BackAction(
                data = data
            )
        }
        factory<Action>(named(BusinessSuccessAction.IDENTIFIER)) { (data: JsonObject) ->
            BusinessSuccessAction(
                data = data
            )
        }
        factory<Action>(named(NavigateAction.IDENTIFIER)) { (data: JsonObject) ->
            NavigateAction(
                data = data,
                globalStateManager = get()
            )
        }
        factory<Action>(named(ToBooleanAction.IDENTIFIER)) { (data: JsonObject) ->
            ToBooleanAction(
                data = data,
                stateManager = get(),
            )
        }
        factory<Action>(named(ToIntAction.IDENTIFIER)) { (data: JsonObject) ->
            ToIntAction(
                data = data,
                stateManager = get(),
            )
        }
        factory<Action>(named(ToStringAction.IDENTIFIER)) { (data: JsonObject) ->
            ToStringAction(
                data = data,
                stateManager = get(),
            )
        }
        factory<Action>(named(MultipleActionsAction.IDENTIFIER)) { (data: JsonObject) ->
            MultipleActionsAction(
                data = data,
                actionParser = get(),
            )
        }
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