package com.example.serverdriveui.service

import com.example.serverdriveui.service.model.ActionModel
import com.example.serverdriveui.service.model.ComponentModel
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.service.model.SdUiRequest
import com.example.serverdriveui.service.model.ValidatorModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SdUiMockService : SdUiService {
    override fun getScreenModel(request: SdUiRequest): Flow<ScreenModel> {
        return flow {
            println(request)
            delay(1000)
            emit(flow(request))
        }
    }
}

private fun flow(request: SdUiRequest) = when (request.screenId) {
    "SignUp.Email" -> emailScreen(request.screenData)
    "SignUp.PersonalInfo" -> personalInfoScreen(request.screenData)
    "SignUp.Password" -> passwordScreen(request.screenData)
    "SignUp.Success" -> successScreen(request.screenData)
    else -> throw IllegalArgumentException("Invalid id(${request.screenId}) for SignUp Flow")
}

//Email -> Personal Info -> Password -> Success

private fun emailScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Email",
    version = "1",
    template = "",
    components = listOf(
        ComponentModel(
            type = "text",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Email")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.Email.emailInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Digite seu email",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    data = mapOf("length" to "3"),
                    id = "SignUp.Email.isEmailFilled",
                    required = listOf(
                        "SignUp.Email.emailInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.Email.isEmailFilled"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "nextScreenId" to "SignUp.PersonalInfo",
                            "screenRequestData" to """{ "SignUp.Email.emailInput" : "email" }""",
                            "screenData" to screenData
                        ),
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Fechar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "close"
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

private fun personalInfoScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "PersonalInfo",
    version = "1",
    template = "",
    components = listOf(
        ComponentModel(
            type = "text",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Informações Pessoais")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.nameInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Nome completo",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    data = mapOf("length" to "3"),
                    id = "SignUp.PersonalInfo.isNameFilled",
                    required = listOf(
                        "SignUp.PersonalInfo.nameInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.documentInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "CPF",
                "textFormatter" to "Documento.CPF"
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    id = "SignUp.PersonalInfo.isCpfValid",
                    data = mapOf("length" to "11"),
                    required = listOf(
                        "SignUp.PersonalInfo.documentInput",
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.phoneInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Telefone",
                "textFormatter" to "Telefone",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    data = mapOf("length" to "11"),
                    id = "SignUp.PersonalInfo.isPhoneFilled",
                    required = listOf(
                        "SignUp.PersonalInfo.phoneInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.PersonalInfo.continueButton"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "nextScreenId" to "SignUp.Password",
                            "screenRequestData" to """{ 
                                "SignUp.PersonalInfo.nameInput" : "name",
                                "SignUp.PersonalInfo.documentInput" : "document", 
                                "SignUp.PersonalInfo.phoneInput" : "phone" 
                                }""".trimMargin(),
                            "screenData" to screenData
                        ),
                    ),
                    validators = listOf(
                        ValidatorModel(
                            type = "allTrue",
                            id = "SignUp.PersonalInfo.continueButton",
                            required = listOf(
                                "SignUp.PersonalInfo.isNameFilled",
                                "SignUp.PersonalInfo.isCpfValid",
                                "SignUp.PersonalInfo.isPhoneFilled",
                            ),
                        )
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Voltar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "back",
                        data = mapOf()
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

private fun passwordScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Password",
    version = "1",
    template = "",
    components = listOf(
        ComponentModel(
            type = "text",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Criar Senha")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PasswordScreen.passwordInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Senha",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Confirmar Senha",
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "nextScreenId" to "SignUp.Success",
                            "screenRequestData" to """{ "SignUp.PasswordScreen.passwordInput" : "password" }""",
                            "screenData" to screenData
                        ),
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Voltar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "back"
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

private fun successScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Success",
    version = "1",
    template = "",
    components = listOf(
        ComponentModel(
            type = "text",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Conta Criada")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "text",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Success")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Fazer Login"),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "businessSuccess",
                        data = mapOf(
                            "screenData" to screenData
                        ),
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

//Email -> Validação
//password -> Validação

/*
atual + dados + proxima

SignUp.PersonalData
Dados de email


chama backend
backend transforma dados em uma model
chama backend do backend para validar se o email ja foi usado
se sim
retorna erro com uma nova tela e descrição do que deu errado
se não
chama proxima tela passando os dados do email

 */


