package com.carlosdiestro.needit.core.design_system.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.images.NeedItAvatar
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

object TopAppBarSpecs {
    enum class TopAppBarSize {
        Default,
        Large
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun defaultColors(): TopAppBarColors {
        return TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun secondaryColors(): TopAppBarColors {
        return TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NeedItBaseTopAppBar(
    title: @Composable () -> Unit = {},
    onNavigateClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    colors: TopAppBarColors,
    topAppBarSize: TopAppBarSpecs.TopAppBarSize = TopAppBarSpecs.TopAppBarSize.Default
) {
    when (topAppBarSize) {
        TopAppBarSpecs.TopAppBarSize.Default -> {
            TopAppBar(
                title = title,
                navigationIcon = {
                    if (onNavigateClick != null) {
                        NeedItFilledIconButton(
                            icon = MaterialTheme.icons.Back,
                            onClick = onNavigateClick
                        )
                    }
                },
                colors = colors,
                actions = { actions() }
            )
        }

        TopAppBarSpecs.TopAppBarSize.Large -> {
            MediumTopAppBar(
                title = title,
                navigationIcon = {
                    if (onNavigateClick != null) {
                        NeedItFilledIconButton(
                            icon = MaterialTheme.icons.Back,
                            onClick = onNavigateClick
                        )
                    }
                },
                colors = colors
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItCenteredTopAppBar(
    title: String,
    onNavigateClick: () -> Unit,
    colors: TopAppBarColors = TopAppBarSpecs.defaultColors(),
    actions: @Composable () -> Unit
) {
    NeedItBaseTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onNavigateClick = onNavigateClick,
        colors = colors,
        actions = actions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItLargeTopAppBar(
    title: String,
    colors: TopAppBarColors = TopAppBarSpecs.defaultColors(),
    onNavigateClick: () -> Unit,
) {
    NeedItBaseTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        onNavigateClick = onNavigateClick,
        colors = colors,
        topAppBarSize = TopAppBarSpecs.TopAppBarSize.Large
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItAvatarTopAppBar(
    title: String,
    avatarUrl: String,
    colors: TopAppBarColors = TopAppBarSpecs.defaultColors(),
    onNavigateClick: () -> Unit
) {
    NeedItBaseTopAppBar(
        title = {
            AvatarAndTitle(title = title, avatarUrl = avatarUrl)
        },
        colors = colors,
        onNavigateClick = onNavigateClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItTopAppBar(
    title: String? = null,
    onNavigateClick: (() -> Unit)? = null,
    colors: TopAppBarColors = TopAppBarSpecs.defaultColors(),
    actions: @Composable () -> Unit = {}
) {
    NeedItBaseTopAppBar(
        title = {
            if (title != null) Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        onNavigateClick = onNavigateClick,
        colors = colors,
        actions = actions
    )
}

@Composable
private fun AvatarAndTitle(
    title: String,
    avatarUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS)
    ) {
        NeedItAvatar(imageUrl = avatarUrl)
        Text(text = title, style = MaterialTheme.typography.titleSmall)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NeedItTopAppBarPreview() {
    NeedItTheme {
        Column {
            NeedItCenteredTopAppBar(
                title = "@kalladinstormblessed",
                onNavigateClick = {},
                actions = {
                    NeedItFilledIconButton(icon = MaterialTheme.icons.Sort, onClick = {})
                }
            )
            NeedItLargeTopAppBar(
                title = stringResource(id = R.string.group_settings_title, "Syl Honor"),
                onNavigateClick = {}
            )
            NeedItAvatarTopAppBar(
                title = "@kalladinstormblessed",
                avatarUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnewprofilepic" +
                        ".com%2F&psig=AOvVaw1LSPrUiXS7M0GTMKMgBoHD&ust=1686905129455000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCMiyrMrxxP8CFQAAAAAdAAAAABAE",
                onNavigateClick = {}
            )
            NeedItTopAppBar(
                title = stringResource(id = R.string.home_title),
                onNavigateClick = {},
                actions = {
                    NeedItFilledIconButton(icon = MaterialTheme.icons.Sort, onClick = {})
                    NeedItFilledIconButton(icon = MaterialTheme.icons.Sort, onClick = {})
                }
            )
        }
    }
}