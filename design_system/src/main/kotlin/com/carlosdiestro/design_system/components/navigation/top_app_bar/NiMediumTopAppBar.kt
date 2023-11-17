package com.carlosdiestro.design_system.components.navigation.top_app_bar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.components.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.components.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiMediumTopAppBar(
    @StringRes titleId: Int,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
    colors: TopAppBarColors = NiTopAppBarSpecs.Color.transparent(),
    actions: @Composable() (RowScope.() -> Unit),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    MediumTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleId)
            )
        },
        navigationIcon = {
            NiIconButton(
                icon = MaterialTheme.icons.Back,
                colors = NiIconButtonSpecs.Color.transparentPrimary(),
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
            titleId = -1,
            onNavigationClick = {},
            actions = {
                NiIconButton(
                    icon = MaterialTheme.icons.Delete,
                    colors = NiIconButtonSpecs.Color.transparentPrimary(),
                    onClick = {}
                )
            }
        )
    }
}