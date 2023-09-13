package com.carlosdiestro.needit.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.ButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.IconButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItOutlinedIconButton
import com.carlosdiestro.needit.core.design_system.components.images.NeedItAvatar
import com.carlosdiestro.needit.core.design_system.components.lists.NeedItWishGrid
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.components.navigation.TopAppBarSpecs
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun ProfileRoute(
    isUserGuest: Boolean,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreen(
        state = state,
        isUserGuest = isUserGuest,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}

@Composable
private fun ProfileScreen(
    state: ProfileState,
    isUserGuest: Boolean,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    when (isUserGuest) {
        true -> SuggestSignIn(
            onSignInClick = {}
        )
        false -> ProfileScreenUser(
            state = state,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenUser(
    state: ProfileState,
    onItemClick: (Long) -> Unit,
    onItemLongClick: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            NeedItTopAppBar(
                colors = TopAppBarSpecs.secondaryColors(),
                actions = {
                    NeedItFilledIconButton(
                        icon = MaterialTheme.icons.Sort,
                        colors = IconButtonSpecs.secondaryColors(),
                        onClick = {}
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val gridState = rememberLazyGridState()

            ProfileHeader(
                imageUrl = state.userInfo?.profilePictureUrl.orEmpty(),
                email = state.userInfo?.email.orEmpty(),
                username = state.userInfo?.username.orEmpty()
            )
            when (state.showEmptyScreen) {
                true -> {
                    ProfileEmptyState()
                }
                false -> {
                    NeedItWishGrid(
                        state = gridState,
                        wishes = state.wishes,
                        onItemClick = onItemClick,
                        onItemLongClick = onItemLongClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    imageUrl: String,
    email: String,
    username: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .padding(bottom = MaterialTheme.dimensions.spacingM)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.dimensions.spacingXL,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NeedItAvatar(
                imageUrl = imageUrl,
                size = IconButtonSpecs.iconExtraExtraLarge
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXXS))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NameAndTag(username = username, tag = email)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.dimensions.spacingM,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NeedItOutlinedIconButton(
                icon = MaterialTheme.icons.Settings,
                onClick = {}
            )
            NeedItFilledButton(
                labelId = R.string.button_edit_profile,
                size = ButtonSpecs.LargeHeight,
                onClick = {}
            )
            NeedItOutlinedIconButton(
                icon = MaterialTheme.icons.Notification,
                onClick = {}
            )
        }
    }
}

@Composable
private fun NameAndTag(
    username: String,
    tag: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = username,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = tag,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProfileEmptyState() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = stringResource(id = R.string.profile_empty),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(MaterialTheme.dimensions.spacingXXL)
        )
    }
}

@Composable
fun SuggestSignIn(
    onSignInClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXL),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.profile_guest),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
        NeedItFilledButton(
            labelId = R.string.button_login,
            onClick = onSignInClick
        )
    }
}