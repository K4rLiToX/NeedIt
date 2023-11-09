package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.shape

@Composable
fun NiWishInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    price: String,
    description: String,
    size: String? = null,
    color: String? = null,
    isbn: String? = null,
    actions: @Composable RowScope.() -> Unit
) {
    Card(
        modifier = modifier
    ) {
        NiWishCardInfoDetails(
            title = title,
            subtitle = subtitle,
            price = price,
            description = description,
            size = size,
            color = color,
            isbn = isbn,
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXL))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .padding(bottom = MaterialTheme.dimensions.spacingM),
            content = actions
        )
    }
}

@Composable
internal fun NiWishCardInfoDetails(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    price: String,
    description: String,
    size: String?,
    color: String?,
    isbn: String?,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .padding(top = MaterialTheme.dimensions.spacingL)
    ) {
        NiWishCardInfoDetailsHeader(
            title = title,
            subtitle = subtitle,
            price = price,
            modifier = Modifier.fillMaxWidth()
        )
        if (description.isNotEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (!size.isNullOrEmpty() || !color.isNullOrEmpty() || !isbn.isNullOrEmpty()) {
            NiWishCardInfoDetailsPills(
                size = size,
                color = color,
                isbn = isbn,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
internal fun NiWishCardInfoDetailsHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    price: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXS),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.width(70.dp))
        if (price.isNotEmpty() && price != "0.0") {
            Text(
                text = price,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
internal fun NiWishCardInfoDetailsPills(
    modifier: Modifier = Modifier,
    size: String?,
    color: String?,
    isbn: String?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shape.full
            )
            .padding(
                horizontal = MaterialTheme.dimensions.spacingL,
                vertical = MaterialTheme.dimensions.spacingXS
            )
    ) {
        if (!size.isNullOrEmpty()) {
            NiPill(labelId = R.string.item_details_size, value = size, isColor = false)
        }
        if (!color.isNullOrEmpty()) {
            NiPill(labelId = R.string.item_details_color, value = color, isColor = true)
        }
        if (!isbn.isNullOrEmpty()) {
            NiPill(labelId = R.string.item_details_isbn, value = isbn, isColor = false)
        }
    }
}

@Composable
internal fun NiPill(
    modifier: Modifier = Modifier,
    @StringRes labelId: Int,
    value: String,
    isColor: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.dimensions.spacingM,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (isColor)
            NiPillColorValue(color = value)
        else
            NiPillTextValue(value = value)
    }
}

@Composable
internal fun NiPillTextValue(
    modifier: Modifier = Modifier,
    value: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shape.full)
            .widthIn(min = 40.dp)
            .heightIn(min = 40.dp)
            .wrapContentSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun NiPillColorValue(
    modifier: Modifier = Modifier,
    color: String
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shape.full)
            .width(40.dp)
            .height(40.dp)
            .background(Color(color.toColorInt()))
            .padding(5.dp)
            .background(Color(color.toColorInt()))
    )
}