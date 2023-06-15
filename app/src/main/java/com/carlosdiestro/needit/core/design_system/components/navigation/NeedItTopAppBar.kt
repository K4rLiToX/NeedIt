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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.avatars.NeedItAvatar
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing

private enum class TopAppBarSize {
    Default,
    Large
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NeedItBaseTopAppBar(
    title: @Composable () -> Unit = {},
    onNavigateClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    topAppBarSize: TopAppBarSize = TopAppBarSize.Default
) {
    when (topAppBarSize) {
        TopAppBarSize.Default -> {
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                actions = { actions() }
            )
        }

        TopAppBarSize.Large -> {
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Composable
fun NeedItCenteredTopAppBar(
    title: String,
    onNavigateClick: () -> Unit,
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
        actions = actions
    )
}

@Composable
fun NeedItLargeTopAppBar(
    title: String,
    onNavigateClick: () -> Unit,
) {
    NeedItBaseTopAppBar(
        title = {
            Text(text = title)
        },
        onNavigateClick = onNavigateClick,
        topAppBarSize = TopAppBarSize.Large
    )
}

@Composable
fun NeedItAvatarTopAppBar(
    title: String,
    avatarUrl: String,
    onNavigateClick: () -> Unit
) {
    NeedItBaseTopAppBar(
        title = {
            AvatarAndTitle(title = title, avatarUrl = avatarUrl)
        },
        onNavigateClick = onNavigateClick
    )
}

@Composable
fun NeedItTopAppBar(
    title: String? = null,
    onNavigateClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit
) {
    NeedItBaseTopAppBar(
        title = {
            if (title != null) Text(text = title)
        },
        onNavigateClick = onNavigateClick,
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
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
    ) {
        NeedItAvatar(imageUrl = avatarUrl)
        Text(text = title, style = MaterialTheme.typography.titleSmall)
    }
}

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