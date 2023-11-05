package com.carlosdiestro.needit.features.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosdiestro.needit.R
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
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(
                    horizontal = MaterialTheme.dimensions.spacingM,
                    vertical = MaterialTheme.dimensions.spacingL
                )
        ) {
            Text(
                text = stringResource(id = R.string.settings_display_section),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiLabeledSwitch(
                labelId = R.string.settings_display_section_use_system_scheme,
                checked = true,
                onCheckedChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiLabeledSwitch(
                labelId = R.string.settings_display_section_night_mode,
                checked = false,
                onCheckedChange = {},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))

            Text(
                text = stringResource(id = R.string.settings_notifications_section),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiLabeledSwitch(
                labelId = R.string.settings_notifications_section_friend_requests,
                checked = true,
                onCheckedChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiLabeledSwitch(
                labelId = R.string.settings_notifications_section_addition_to_groups,
                checked = false,
                onCheckedChange = {},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))

            Text(
                text = stringResource(id = R.string.settings_about_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiTitleWithSubtitle(
                title = stringResource(id = R.string.settings_about_section_version_and_updates),
                subtitle = "v1.0.0",
                onClick = onVersionClick
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiTitleWithSubtitle(
                title = stringResource(id = R.string.settings_about_section_privacy_policy),
                onClick = onPrivatePolicyClick
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingM))
            NiTitleWithSubtitle(
                title = stringResource(id = R.string.settings_about_section_terms_of_use),
                onClick = onTermsOfUseClick
            )
        }
    }
}