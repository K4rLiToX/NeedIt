package com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatar
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatarSpecs
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButton
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiMainTopAppBar(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit,
    colors: TopAppBarColors = NiTopAppBarSpecs.Color.transparent(),
    accountImageUrl: String? = null,
    onAccountClick: () -> Unit,
    topAppBarState: TopAppBarState = rememberTopAppBarState()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    CenterAlignedTopAppBar(
        title = {
            Icon(
                painter = painterResource(id = MaterialTheme.icons.NeedIt),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
        },
        navigationIcon = {
            NiIconButton(
                icon = MaterialTheme.icons.Notification,
                colors = NiIconButtonSpecs.Color.transparentPrimary(),
                onClick = onNotificationClick
            )
        },
        colors = colors,
        actions = {
            NiAvatar(
                imageUrl = accountImageUrl,
                size = NiAvatarSpecs.Medium,
                onClick = onAccountClick,
                modifier = Modifier.padding(end = MaterialTheme.dimensions.spacingM)
            )
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiMainTopAppBarPreview() {
    NeedItTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NiMainTopAppBar(
                onNotificationClick = {},
                accountImageUrl = "https://lh3.googleusercontent" +
                        ".com/a/ACg8ocIAt4DgDPUFisU08VoLLXtS06lloC8ftW_GpGN1AkekwWY=s96-c",
                onAccountClick = {}
            )
            NiMainTopAppBar(
                onNotificationClick = {},
                onAccountClick = {}
            )
        }
    }
}