package com.vini.featurehome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vini.featurehome.contentprovider.ContentProvider

class HomeContentProviderImpl(private val composableProviders: List<ContentProvider>) {
    private val content : Map<Int, @Composable (Modifier) -> Unit> = composableProviders.associate { composableProviders.indexOf(it) to it.content }
    private val icons = composableProviders.map { it.selectedIcon }
    private val unselectedIcons = composableProviders.map { it.unselectedIcon }
    private val titles = composableProviders.map { it.title }

    fun getContent(contentIndex: Int = 0) = HomeContentModel(
        content = content[contentIndex] ?: {},
        icons = icons.mapIndexed { index, imageVector -> if(contentIndex == index) imageVector else unselectedIcons[index] },
        titles = titles
    )
}