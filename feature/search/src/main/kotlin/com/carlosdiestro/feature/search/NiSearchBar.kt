package com.carlosdiestro.feature.search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.avatars.NiAvatar
import com.carlosdiestro.design_system.avatars.NiAvatarSpecs
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons

@Composable
fun NiSearchBarRoute(
    accountImageUrl: String?,
    onNotificationClick: () -> Unit,
    onAccountClick: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.isSearchBarEnabled.collectAsStateWithLifecycle()
    val query = viewModel.query
    NiSearchBar(
        isSearchBarEnabled = state,
        accountImageUrl = accountImageUrl,
        query = query,
        onQueryChange = viewModel::onQueryChange,
        onNotificationClick = onNotificationClick,
        onAccountClick = onAccountClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NiSearchBar(
    isSearchBarEnabled: Boolean,
    accountImageUrl: String?,
    query: String,
    onQueryChange: (String) -> Unit,
    onNotificationClick: () -> Unit,
    onAccountClick: () -> Unit,
) {
    var active by rememberSaveable {
        mutableStateOf(false)
    }

    val horizontalPadding by animateDpAsState(
        targetValue = if (active) 0.dp else MaterialTheme.dimensions.spacingM,
        label = "Friend SearchBar Horizontal Padding"
    )

    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = active,
        onActiveChange = { active = it },
        leadingIcon = {
            if (active) {
                NiIconButton(
                    icon = MaterialTheme.icons.Back,
                    colors = NiIconButtonSpecs.Color.transparentPrimary(),
                    onClick = {
                        onQueryChange("")
                        active = false
                    }
                )
            } else {
                NiIconButton(
                    icon = MaterialTheme.icons.Notification,
                    colors = NiIconButtonSpecs.Color.transparentPrimary(),
                    onClick = onNotificationClick
                )
            }
        },
        placeholder = {
            Text(
                text = Localization.People.Search,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            if (active) {
                NiIconButton(
                    icon = MaterialTheme.icons.Close,
                    colors = NiIconButtonSpecs.Color.transparentPrimary(),
                    onClick = { onQueryChange("") }
                )
            } else {
                NiAvatar(
                    imageUrl = accountImageUrl,
                    size = NiAvatarSpecs.Medium,
                    onClick = onAccountClick,
                    modifier = Modifier.padding(end = MaterialTheme.dimensions.spacingM)
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        ),
        enabled = isSearchBarEnabled,
        shadowElevation = 0.dp,
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth()
    ) {

    }
}