package com.carlosdiestro.needit.features.wish_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItOutlinedIconButton
import com.carlosdiestro.needit.core.design_system.components.cards.Currency
import com.carlosdiestro.needit.core.design_system.components.cards.NeedItItemDetailCard
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.theme.button
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing

@Composable
fun WishDetailsRoute(
    onBackClick: () -> Unit,
    viewModel: WishDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    WishDetailsScreen(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
private fun WishDetailsScreen(
    state: WishDetailsUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NeedItTopAppBar(
                onNavigateClick = onBackClick
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.m)
                .padding(bottom = MaterialTheme.spacing.l)
        ) {
            WishInformation(
                imageUrl = state.imageUrl,
                title = state.title,
                subtitle = state.subtitle,
                price = state.price,
                description = state.description,
                size = state.size,
                color = state.color,
                isbn = state.isbn
            )
            WishActions(
                webUrl = state.webUrl,
                isShared = state.isShared
            )
        }
    }
}

@Composable
private fun WishInformation(
    imageUrl: String,
    title: String,
    subtitle: String,
    price: String,
    description: String,
    size: String?,
    color: String?,
    isbn: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        NeedItItemDetailCard(
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            price = price,
            currency = Currency("€", true),
            description = description,
            size = size,
            color = color,
            isbn = isbn
        )
    }
}

@Composable
private fun WishActions(
    webUrl: String,
    isShared: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NeedItOutlinedIconButton(
            icon = MaterialTheme.icons.Link,
            onClick = {}
        )
        NeedItFilledButton(
            labelId = if (!isShared) R.string.button_share else R.string.button_keep_private,
            leadingIcon = if (!isShared) MaterialTheme.icons.Share else MaterialTheme.icons.Lock,
            modifier = Modifier
                .width(200.dp)
                .height(MaterialTheme.dimensions.button.largeHeight),
            onClick = {}
        )
        NeedItOutlinedIconButton(
            icon = MaterialTheme.icons.Edit,
            onClick = {}
        )
    }
}