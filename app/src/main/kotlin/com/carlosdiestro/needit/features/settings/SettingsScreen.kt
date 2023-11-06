package com.carlosdiestro.needit.features.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.cards.NiSettingSectionCard
import com.carlosdiestro.needit.core.design_system.components.cards.NiTitleWithSubtitle
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMediumTopAppBar
import com.carlosdiestro.needit.core.design_system.components.selectors.switch.NiLabeledSwitch
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRoute(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = rememberSettingsUiState()
    SettingsScreen(
        dataState = dataState,
        uiState = uiState,
        onBackClick = onBackClick,
        onVersionClick = onVersionClick,
        onPrivatePolicyClick = onPrivatePolicyClick,
        onTermsOfUseClick = onTermsOfUseClick,
        onUseSystemSchemeClick = viewModel::updateUseSystemScheme,
        onNightModeClick = viewModel::updateNightMode,
        onFriendRequestsClick = viewModel::updateFriendRequests,
        onAdditionToGroupsClick = viewModel::updateAdditionToGroups
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    dataState: SettingsDataState,
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    onUseSystemSchemeClick: () -> Unit,
    onNightModeClick: () -> Unit,
    onFriendRequestsClick: () -> Unit,
    onAdditionToGroupsClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NiMediumTopAppBar(
                titleId = R.string.settings_title,
                onNavigationClick = onBackClick,
                topAppBarState = uiState.topAppBarState,
                scrollBehavior = uiState.scrollBehavior,
                actions = {}
            )
        },
        modifier = Modifier
            .nestedScroll(uiState.scrollBehavior.nestedScrollConnection)
    ) {
        println("System Scheme: ${dataState.useSystemScheme}")
        LazyColumn(
            state = uiState.lazyListState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
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
                    NiLabeledSwitch(
                        labelId = R.string.settings_display_section_use_system_scheme,
                        checked = dataState.useSystemScheme,
                        onCheckedChange = { onUseSystemSchemeClick() },
                        modifier = Modifier.fillMaxWidth()
                    )
                    NiLabeledSwitch(
                        labelId = R.string.settings_display_section_night_mode,
                        checked = dataState.isNightMode,
                        enabled = dataState.isNightModeEnabled,
                        onCheckedChange = { onNightModeClick() },
                        modifier = Modifier.fillMaxWidth()
                    )
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