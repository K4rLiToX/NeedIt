package com.carlosdiestro.needit.core.design_system.components.navigation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiIconButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NiIconButtonSpecs
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiMediumTopAppBar(
    @StringRes titleId: Int,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
    colors: TopAppBarColors = NiTopAppBarSpecs.Color.transparent(),
    actions: @Composable() (RowScope.() -> Unit),
    topAppBarState: TopAppBarState = rememberTopAppBarState()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    MediumTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleId)
            )
        },
        navigationIcon = {
            NiIconButton(
                icon = MaterialTheme.icons.Back,
                colors = NiIconButtonSpecs.Color.transparent(),
                onClick = onNavigationClick
            )
        },
        colors = colors,
        actions = actions,
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiMediumTopAppBarPreview() {
    NeedItTheme {
        NiMediumTopAppBar(
            titleId = R.string.settings_title,
            onNavigationClick = {},
            actions = {
                NiIconButton(
                    icon = MaterialTheme.icons.Delete,
                    colors = NiIconButtonSpecs.Color.transparent(),
                    onClick = {}
                )
            }
        )
    }
}