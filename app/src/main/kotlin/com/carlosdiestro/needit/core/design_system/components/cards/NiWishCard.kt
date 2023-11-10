package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.animations.enter
import com.carlosdiestro.needit.core.design_system.components.animations.exit
import com.carlosdiestro.needit.core.design_system.components.container.NiImageContainer
import com.carlosdiestro.needit.core.design_system.components.extensions.gradient
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.shape

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NiWishCard(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = NiWishCardSpecs.Height,
    imageUrl: String,
    shared: Boolean,
    selected: Boolean
) {
    val animatedHorizontalPadding by animateDpAsState(
        targetValue = if (selected) {
            31.dp
        } else {
            0.dp
        },
        label = "padding-horizontal-outer"
    )
    val animatedVerticalPadding by animateDpAsState(
        targetValue = if (selected) {
            MaterialTheme.dimensions.spacingXXL
        } else {
            0.dp
        },
        label = "padding-vertical-outer"
    )
    val animatedBackground by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        } else {
            Color.Transparent
        },
        label = "background"
    )
    Card(
        shape = MaterialTheme.shape.medium,
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    onLongClick()
                }
            )
            .drawBehind {
                drawRect(animatedBackground)
            }
            .padding(
                vertical = animatedVerticalPadding,
                horizontal = animatedHorizontalPadding
            )
    ) {
        NiImageContainer(
            imageUrl = imageUrl,
            modifier = Modifier.fillMaxSize(),
        ) {
            NiWishCardInformation(
                selected = selected,
                shared = shared,
                modifier = Modifier
                    .fillMaxSize()
                    .gradient(Color(0x4D000000))
                    .padding(MaterialTheme.dimensions.spacingXS)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NiWishCard(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = NiWishCardSpecs.Height,
    imageUrl: String,
    title: String,
    price: String,
    reserved: Boolean
) {
    Card(
        shape = MaterialTheme.shape.medium,
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        NiImageContainer(
            imageUrl = imageUrl,
            modifier = Modifier.fillMaxSize()
        ) {
            if (!reserved) {
                NiWishCardInformation(
                    title = title,
                    price = price,
                    modifier = Modifier
                        .fillMaxSize()
                        .gradient(Color(0x4D000000))
                        .padding(MaterialTheme.dimensions.spacingS)
                )
            } else {
                NiWishCardReserved(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0X88000000))
                        .padding(horizontal = MaterialTheme.dimensions.spacingXL)
                )
            }
        }
    }
}

@Composable
internal fun NiWishCardInformation(
    modifier: Modifier = Modifier,
    selected: Boolean,
    shared: Boolean
) {
    val animatedPadding by animateDpAsState(
        targetValue = if (selected) {
            2.dp
        } else {
            MaterialTheme.dimensions.spacingXS
        },
        label = "padding-inner"
    )
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .padding(animatedPadding)
    ) {
        AnimatedVisibility(
            visible = selected,
            enter = enter,
            exit = exit,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = MaterialTheme.icons.Check,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clip(MaterialTheme.shape.full)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }

        if (shared)
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = MaterialTheme.icons.Share,
                    contentDescription = "", tint =
                    MaterialTheme.colorScheme.onPrimary
                )
            }
    }
}

@Composable
internal fun NiWishCardInformation(
    modifier: Modifier = Modifier,
    title: String,
    price: String,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimensions.spacingXS))
            if (price.isNotEmpty()) {
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
internal fun NiWishCardReserved(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.label_reserved),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}