package com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatar
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButton
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    profileImageUrl: String? = null,
    onNavigationClick: () -> Unit,
    navigationIconColor: IconButtonColors = NiIconButtonSpecs.Color.transparentPrimary(),
    colors: TopAppBarColors = NiTopAppBarSpecs.Color.transparent(),
    actions: @Composable() (RowScope.() -> Unit),
    topAppBarState: TopAppBarState = rememberTopAppBarState()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    TopAppBar(
        title = {
            NiTopAppBarTitle(
                title = title,
                profileImageUrl = profileImageUrl
            )
        },
        navigationIcon = {
            NiIconButton(
                icon = MaterialTheme.icons.Back,
                colors = navigationIconColor,
                onClick = onNavigationClick
            )
        },
        colors = colors,
        actions = actions,
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}

@Composable
internal fun NiTopAppBarTitle(
    title: String,
    profileImageUrl: String?,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        if (profileImageUrl != null) {
            NiAvatar(
                imageUrl = profileImageUrl,
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiTopAppBarPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiTopAppBar(
                title = "Kalladin Stormblessed",
                profileImageUrl = "https://lh3.googleusercontent" +
                        ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
                onNavigationClick = {},
                actions = {
                    NiIconButton(
                        icon = MaterialTheme.icons.Edit,
                        onClick = {}
                    )
                    NiIconButton(
                        icon = MaterialTheme.icons.Link,
                        onClick = {}
                    )
                }
            )
            NiTopAppBar(
                title = "Kalladin Stormblessed",
                onNavigationClick = {},
                actions = {
                    NiIconButton(
                        icon = MaterialTheme.icons.Edit,
                        onClick = {}
                    )
                    NiIconButton(
                        icon = MaterialTheme.icons.Link,
                        onClick = {}
                    )
                }
            )
        }
    }
}