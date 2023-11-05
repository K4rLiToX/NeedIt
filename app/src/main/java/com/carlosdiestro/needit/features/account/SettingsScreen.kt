package com.carlosdiestro.needit.features.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.cards.NiSettingSectionCard
import com.carlosdiestro.needit.core.design_system.components.cards.NiTitleWithSubtitle
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiMediumTopAppBar
import com.carlosdiestro.needit.core.design_system.components.selectors.switch.NiLabeledSwitch
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun SettingsRoute(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    SettingsScreen(
        onBackClick = onBackClick,
        onVersionClick = onVersionClick,
        onPrivatePolicyClick = onPrivatePolicyClick,
        onTermsOfUseClick = onTermsOfUseClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    onBackClick: () -> Unit,
    onVersionClick: () -> Unit,
    onPrivatePolicyClick: () -> Unit,
    onTermsOfUseClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            NiMediumTopAppBar(
                titleId = R.string.settings_title,
                onNavigationClick = onBackClick,
                actions = {}
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(
                    horizontal = MaterialTheme.dimensions.spacingM,
                    vertical = MaterialTheme.dimensions.spacingL
                )
        ) {
            NiSettingSectionCard(
                titleId = R.string.settings_display_section,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                NiLabeledSwitch(
                    labelId = R.string.settings_display_section_use_system_scheme,
                    checked = true,
                    onCheckedChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                NiLabeledSwitch(
                    labelId = R.string.settings_display_section_night_mode,
                    checked = false,
                    enabled = false,
                    onCheckedChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
            NiSettingSectionCard(
                titleId = R.string.settings_notifications_section,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                NiLabeledSwitch(
                    labelId = R.string.settings_notifications_section_friend_requests,
                    checked = true,
                    onCheckedChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                NiLabeledSwitch(
                    labelId = R.string.settings_notifications_section_addition_to_groups,
                    checked = false,
                    onCheckedChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
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