package com.example.serverdriveui.ui.actions.manager

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.actions.BackAction
import com.example.serverdriveui.ui.actions.BusinessSuccessAction
import com.example.serverdriveui.ui.actions.CloseAction
import com.example.serverdriveui.ui.actions.ContinueAction
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class ActionManager(private val koinScope: Scope) {

    fun getAction(identifier: String, data: Map<String, String>): Action = when(identifier) {
        ContinueAction.IDENTIFIER -> koinScope.get<ContinueAction> { parametersOf(data) }
        BackAction.IDENTIFIER -> koinScope.get<BackAction> { parametersOf(data) }
        CloseAction.IDENTIFIER -> koinScope.get<CloseAction> { parametersOf(data) }
        BusinessSuccessAction.IDENTIFIER -> koinScope.get<BusinessSuccessAction> { parametersOf(data) }
        else -> unknownAction()
    }

    private fun unknownAction() = object : Action {
        override fun execute(navController: NavHostController) {}
    }
}