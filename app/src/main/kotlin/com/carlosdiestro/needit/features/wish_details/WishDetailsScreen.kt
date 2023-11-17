package com.carlosdiestro.needit.features.wish_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.design_system.components.cards.NiWishInfoCard
import com.carlosdiestro.design_system.components.container.NiImageContainer
import com.carlosdiestro.design_system.components.extensions.gradient
import com.carlosdiestro.design_system.components.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.components.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.components.navigation.top_app_bar.NiTopAppBar
import com.carlosdiestro.design_system.components.navigation.top_app_bar.NiTopAppBarSpecs
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons

@Composable
internal fun WishDetailsRoute(
    onBackClick: () -> Unit,
    onUpdateClick: (Int, String) -> Unit,
    viewModel: WishDetailsViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    WishDetailsScreen(
        dataState = dataState,
        onBackClick = onBackClick,
        onUpdateClick = { onUpdateClick(0, dataState.id) },
        onShareClick = viewModel::shareWish,
        onPrivateClick = viewModel::lockWish
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WishDetailsScreen(
    dataState: WishDetailsDataState,
    onBackClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onShareClick: () -> Unit,
    onPrivateClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NiTopAppBar(
                title = "",
                onNavigationClick = onBackClick,
                navigationIconColor = NiIconButtonSpecs.Color.transparentSecondary(),
                colors = NiTopAppBarSpecs.Color.neutral(),
                actions = {
                    NiIconButton(
                        icon = MaterialTheme.icons.Edit,
                        colors = NiIconButtonSpecs.Color.transparentSecondary(),
                        onClick = onUpdateClick
                    )
                    NiIconButton(
                        icon = MaterialTheme.icons.Link,
                        colors = NiIconButtonSpecs.Color.transparentSecondary(),
                        onClick = {}
                    )
                },
                modifier = Modifier
                    .gradient(
                        color = Color.Black,
                        startY = 0.0F,
                        endY = Float.POSITIVE_INFINITY
                    )
            )
        }
    ) {
        NiImageContainer(
            imageUrl = dataState.imageUrl,
            modifier = Modifier
                .fillMaxSize()
        ) {
            NiWishInfoCard(
                title = dataState.title,
                subtitle = dataState.subtitle,
                price = dataState.price,
                description = dataState.description,
                size = dataState.size,
                color = dataState.color,
                isbn = dataState.isbn,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = MaterialTheme.dimensions.spacingM)
                    .padding(bottom = MaterialTheme.dimensions.spacingM)
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                if (!dataState.isAnonymous) {
                    NiFilledButton(
                        labelId = dataState.actionLabelId,
                        trailIcon = dataState.actionIcon,
                        height = NiButtonSpecs.Height.Large,
                        onClick = {
                            if (dataState.isShared) onPrivateClick()
                            else onShareClick()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}