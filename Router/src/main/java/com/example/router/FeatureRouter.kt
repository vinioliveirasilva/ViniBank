package com.example.router

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.core.os.bundleOf

class FeatureRouter(
    private val context: ComponentActivity
) {
    fun navigate(route: Route) {
        context.startActivity(route.getIntent())
    }

    fun navigateAndFinish(route: Route) {
        context.startActivity(route.getIntent())
        context.finishAfterTransition()
    }

    fun navigate(route: Route, launcher: ActivityResultLauncher<Intent>) {
        launcher.launch(route.getIntent())
    }

    private fun Route.getIntent(): Intent {
        val bundle = bundleOf()
        data?.bundleAction?.invoke(bundle)
        val intent = Intent(id).apply {
            setPackage(context.packageName)
            putExtras(bundle)
        }
        return intent
    }
}