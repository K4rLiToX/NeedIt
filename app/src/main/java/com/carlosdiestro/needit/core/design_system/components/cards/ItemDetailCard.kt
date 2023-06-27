package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.images.NeedItImageContainer
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.spacing

@Composable
fun NeedItItemDetailCard(
    imageUrl: String,
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency,
    description: String,
    size: String? = null,
    color: String? = null,
    isbn: String? = null
) {
    NeedItImageContainer(
        imageUrl = imageUrl,
        modifier = Modifier
            .fillMaxSize(),
        cardContent = {
            if (!size.isNullOrEmpty() && !color.isNullOrEmpty()) {
                NeedItFashionInfoCard(
                    title = title,
                    subtitle = subtitle,
                    price = price,
                    currency = currency,
                    description = description,
                    size = size,
                    color = color
                )
                return@NeedItImageContainer
            }
            if (!isbn.isNullOrEmpty()) {
                NeedItBookInfoCard(
                    title = title,
                    subtitle = subtitle,
                    price = price,
                    currency = currency,
                    description = description,
                    isbn = isbn
                )
                return@NeedItImageContainer
            }
            NeedItOtherInfoCard(
                title = title,
                subtitle = subtitle,
                price = price,
                currency = currency,
                description = description
            )
        }
    )
}

@Composable
private fun NeedItBaseItemInfoCard(
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency,
    description: String,
    size: String? = null,
    color: String? = null,
    isbn: String? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.m),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(MaterialTheme.spacing.m)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                horizontal = MaterialTheme.spacing.m,
                vertical = MaterialTheme.spacing.l
            )
    ) {
        ItemHeader(
            title = title,
            subtitle = subtitle,
            price = price,
            currency = currency
        )
        if (description.isNotEmpty()) Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        if (!size.isNullOrEmpty() && !color.isNullOrEmpty()) ItemSizeAndColor(
            size = size,
            color = color
        )
        if (!isbn.isNullOrEmpty()) ItemIsbn(isbn = isbn)
    }
}

@Composable
private fun NeedItFashionInfoCard(
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency,
    description: String,
    size: String,
    color: String
) {
    NeedItBaseItemInfoCard(
        title = title,
        subtitle = subtitle,
        price = price,
        currency = currency,
        description = description,
        size = size,
        color = color
    )
}

@Composable
private fun NeedItBookInfoCard(
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency,
    description: String,
    isbn: String
) {
    NeedItBaseItemInfoCard(
        title = title,
        subtitle = subtitle,
        price = price,
        currency = currency,
        description = description,
        isbn = isbn
    )
}

@Composable
private fun NeedItOtherInfoCard(
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency,
    description: String,
) {
    NeedItBaseItemInfoCard(
        title = title,
        subtitle = subtitle,
        price = price,
        currency = currency,
        description = description
    )
}

@Composable
private fun ItemHeader(
    title: String,
    subtitle: String,
    price: Double,
    currency: Currency
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxs),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(0.65f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        PriceText(
            price = price,
            currency = currency,
            textStyle = MaterialTheme.typography.headlineMedium,
            textColor = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun ItemSizeAndColor(
    size: String,
    color: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Pill(
            label = stringResource(id = R.string.item_details_size),
            value = size,
        )
        Pill(
            label = stringResource(id = R.string.item_details_color),
            value = color,
        )
    }
}

@Composable
private fun ItemIsbn(
    isbn: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Pill(
            label = stringResource(id = R.string.item_details_isbn),
            value = isbn,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Pill(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.m,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(100)
            )
            .padding(
                horizontal = MaterialTheme.spacing.m,
                vertical = MaterialTheme.spacing.xs
            )
            .defaultMinSize(minWidth = 127.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colorScheme.background)
                .padding(MaterialTheme.spacing.s)
        )
    }
}

@Preview
@Composable
private fun NeedItFashionCardPreview() {
    NeedItTheme {
        NeedItFashionInfoCard(
            title = "Scarf",
            subtitle = "Adidas",
            price = 35.0,
            currency = Currency(symbol = "€", isRightPositioned = true),
            description = "",
            size = "XL",
            color = "White"
        )
    }
}

@Preview
@Composable
private fun NeedItBookCardPreview() {
    NeedItTheme {
        NeedItBookInfoCard(
            title = "The Way of the Kings",
            subtitle = "Brandon Sanderson",
            price = 35.0,
            currency = Currency(symbol = "€", isRightPositioned = true),
            description = "",
            isbn = "56987645689876"
        )
    }
}

@Preview
@Composable
private fun NeedItItemDetailCardPreview() {
    NeedItTheme {
        NeedItItemDetailCard(
            imageUrl = "",
            title = "Switch",
            subtitle = "Nintendo",
            price = 200.0,
            currency = Currency(symbol = "€", isRightPositioned = true),
            description = "The blue one, not the red one"
        )
    }
}