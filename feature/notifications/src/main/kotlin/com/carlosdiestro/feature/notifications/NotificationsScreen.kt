package com.carlosdiestro.feature.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.lists.NiFriendRequestsList
import com.carlosdiestro.design_system.navigation.top_app_bar.NiMediumTopAppBar
import com.carlosdiestro.design_system.theme.dimensions

@Composable
fun NotificationsRoute(
    onBackClick: () -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotificationScreen(
        state = state,
        onBackClick = onBackClick,
        onAcceptClick = viewModel::acceptRequest,
        onRejectClick = viewModel::rejectRequest,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationScreen(
    state: NotificationDataState,
    onBackClick: () -> Unit,
    onAcceptClick: (String) -> Unit,
    onRejectClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            NiMediumTopAppBar(
                title = Localization.Notifications.Title,
                onNavigationClick = onBackClick,
                actions = {}
            )
        }
    ) {
        if (state.empty) {
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        } else {
            NiFriendRequestsList(
                requests = state.notifications,
                onAcceptRequest = onAcceptClick,
                onRejectRequest = onRejectClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = MaterialTheme.dimensions.spacingM)
            )
        }
    }
}

@Composable
private fun EmptyScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = Localization.Notifications.FriendRequestEmpty,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.spacingXXL)
        )
    }
}