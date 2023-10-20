package com.carlosdiestro.needit.features.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatar
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatarSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.needit.core.design_system.components.cards.NiAccountExtraInfoCard
import com.carlosdiestro.needit.core.design_system.components.icon_buttons.NiIconButton
import com.carlosdiestro.needit.core.design_system.components.navigation.top_app_bar.NiTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun AccountRoute(
    onBackClick: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    AccountScreen(
        dataState = dataState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountScreen(
    dataState: AccountDataState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NiTopAppBar(
                title = "",
                onNavigationClick = onBackClick,
                actions = {
                    NiIconButton(
                        icon = MaterialTheme.icons.Delete,
                        onClick = {}
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .padding(bottom = MaterialTheme.dimensions.spacingM)
        ) {
            AccountInformation(
                profilePictureUrl = dataState.profilePictureUrl,
                name = dataState.name,
                email = dataState.email,
                birthday = dataState.birthday,
                currency = dataState.currency
            )
            NiFilledButton(
                labelId = R.string.button_logout,
                leadIcon = MaterialTheme.icons.Logout,
                height = NiButtonSpecs.Height.Large,
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            )
        }
    }
}

@Composable
internal fun AccountInformation(
    profilePictureUrl: String,
    name: String,
    email: String,
    birthday: String,
    currency: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXL),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        AccountInformationUser(
            profilePictureUrl = profilePictureUrl,
            name = name,
            email = email
        )
        AccountInformationExtra(
            birthday = birthday,
            currency = currency
        )
    }
}

@Composable
internal fun AccountInformationUser(
    profilePictureUrl: String,
    name: String,
    email: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        NiAvatar(
            imageUrl = profilePictureUrl,
            size = NiAvatarSpecs.ExtraLarge
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingS))
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXS))
        Text(
            text = email,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
internal fun AccountInformationExtra(
    birthday: String,
    currency: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        NiAccountExtraInfoCard(
            labelId = R.string.profile_birthday,
            value = birthday,
            icon = MaterialTheme.icons.Birthday
        )
        NiAccountExtraInfoCard(
            labelId = R.string.profile_currency,
            value = currency,
            icon = MaterialTheme.icons.Birthday
        )
    }
}