package com.carlosdiestro.design_system.navigation.top_app_bar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.theme.NeedItTheme
import com.carlosdiestro.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiMediumTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
    colors: TopAppBarColors = NiTopAppBarSpecs.Color.transparent(),
    actions: @Composable() (RowScope.() -> Unit),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title
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
            title = Localization.Settings.Title,
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