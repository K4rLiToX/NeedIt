package com.carlosdiestro.needit.features.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.ThemeConfigPlo
import com.carlosdiestro.needit.core.design_system.components.buttons.NiTextButton
import com.carlosdiestro.needit.core.design_system.components.cards.NiSettingSectionCard
import com.carlosdiestro.needit.core.design_system.components.cards.NiTitleWithSubtitle
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMediumTopAppBar
import com.carlosdiestro.needit.core.design_system.components.selectors.switch.NiLabeledSwitch
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsRoute(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = rememberSettingsUiState()
    when (dataState) {
        SettingsState.Loading -> Unit
        is SettingsState.Success -> {
            SettingsScreen(
                dataState = dataState,
                uiState = uiState,
                onBackClick = onBackClick,
                onVersionClick = onVersionClick,
                onPrivatePolicyClick = onPrivatePolicyClick,
                onTermsOfUseClick = onTermsOfUseClick,
                onThemeConfigSelected = viewModel::updateThemeConfig,
                onFriendRequestsClick = viewModel::updateFriendRequests,
                onAdditionToGroupsClick = viewModel::updateAdditionToGroups
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    dataState: SettingsState,
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    onThemeConfigSelected: (ThemeConfigPlo) -> Unit,
    onFriendRequestsClick: () -> Unit,
    onAdditionToGroupsClick: () -> Unit
) {
    val selectedTheme = (dataState as SettingsState.Success).selectedTheme

    if (uiState.shouldShowThemeDialog) {
        AlertDialog(
            onDismissRequest = uiState::closeThemeDialog,
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .clip(MaterialTheme.shape.large)
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)),
            title = {
                Text(
                    text = stringResource(id = R.string.settings_display_section_theme),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                ) {
                    ThemeConfigPlo.entries.forEach { themeConfig ->
                        ThemeDialogChooserRow(
                            labelId = themeConfig.labelId,
                            selected = themeConfig == selectedTheme,
                            onClick = { onThemeConfigSelected(themeConfig) }
                        )
                    }
                }
            },
            confirmButton = {
                NiTextButton(
                    labelId = R.string.button_ok,
                    onClick = uiState::closeThemeDialog
                )
            }
        )
    }

    Scaffold(
        topBar = {
            NiMediumTopAppBar(
                titleId = R.string.settings_title,
                onNavigationClick = onBackClick,
                scrollBehavior = uiState.scrollBehavior,
                actions = {}
            )
        },
        modifier = Modifier
            .nestedScroll(uiState.scrollBehavior.nestedScrollConnection)
    ) {
        LazyColumn(
            state = uiState.lazyListState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(
                    horizontal = MaterialTheme.dimensions.spacingM,
                    vertical = MaterialTheme.dimensions.spacingL
                )
        ) {
            item {
                NiSettingSectionCard(
                    titleId = R.string.settings_display_section,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                uiState.openThemeDialog()
                            }
                            .padding(
                                horizontal = MaterialTheme.dimensions.spacingM,
                                vertical = MaterialTheme.dimensions.spacingM
                            )
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings_display_section_theme),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = stringResource(id = selectedTheme.labelId),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            item {
                NiSettingSectionCard(
                    titleId = R.string.settings_notifications_section,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    NiLabeledSwitch(
                        labelId = R.string.settings_notifications_section_friend_requests,
                        checked = true,
                        onCheckedChange = { onFriendRequestsClick() },
                        modifier = Modifier.fillMaxWidth()
                    )
                    NiLabeledSwitch(
                        labelId = R.string.settings_notifications_section_addition_to_groups,
                        checked = false,
                        onCheckedChange = { onAdditionToGroupsClick() },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                NiSettingSectionCard(
                    titleId = R.string.settings_about_section,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    NiTitleWithSubtitle(
                        title = stringResource(id = R.string.settings_about_section_version_and_updates),
                        subtitle = "v1.0.0",
                        onClick = onVersionClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                    NiTitleWithSubtitle(
                        title = stringResource(id = R.string.settings_about_section_privacy_policy),
                        onClick = onPrivatePolicyClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                    NiTitleWithSubtitle(
                        title = stringResource(id = R.string.settings_about_section_terms_of_use),
                        onClick = onTermsOfUseClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeDialogChooserRow(
    @StringRes labelId: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}