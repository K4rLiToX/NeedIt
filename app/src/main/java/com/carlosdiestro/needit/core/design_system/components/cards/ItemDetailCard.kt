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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.images.NeedItImageContainer
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun NeedItItemDetailCard(
    imageUrl: String,
    title: String,
    subtitle: String,
    price: String,
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
            if (!size.isNullOrEmpty() || !color.isNullOrEmpty()) {
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
            if (isbn != null) {
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
    price: String,
    currency: Currency,
    description: String,
    size: String? = null,
    color: String? = null,
    isbn: String? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(MaterialTheme.dimensions.spacingM)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                horizontal = MaterialTheme.dimensions.spacingM,
                vertical = MaterialTheme.dimensions.spacingL
            )
    ) {
        ItemHeader(
            title = title,
            subtitle = subtitle,
            price = price,
            currency = currency
        )
        if (description.isNotEmpty())
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        if (!size.isNullOrEmpty() || !color.isNullOrEmpty())
            ItemSizeAndColor(
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
    price: String,
    currency: Currency,
    description: String,
    size: String?,
    color: String?
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
    price: String,
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
    price: String,
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
    price: String,
    currency: Currency
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXS),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(0.60f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (price != "0.0") {
            PriceText(
                price = price,
                currency = currency,
                textStyle = MaterialTheme.typography.headlineMedium,
                textColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ItemSizeAndColor(
    size: String?,
    color: String?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (!size.isNullOrEmpty()) {
            Pill(
                label = stringResource(id = R.string.item_details_size),
                value = size,
            )
        }
        if (!color.isNullOrEmpty()) {
            Pill(
                label = stringResource(id = R.string.item_details_color),
                value = color,
            )
        }
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
            MaterialTheme.dimensions.spacingM,
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
                horizontal = MaterialTheme.dimensions.spacingM,
                vertical = MaterialTheme.dimensions.spacingXXS
            )
            .defaultMinSize(minWidth = 100.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colorScheme.background)
                .padding(MaterialTheme.dimensions.spacingS)
        )
    }
}

class Currency(
    val symbol: String,
    val isRightPositioned: Boolean
)

@Composable
fun PriceText(
    price: String,
    currency: Currency,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier = Modifier
) {
    val priceText = if (!currency.isRightPositioned) {
        price.round().addPrefix(currency.symbol)
    } else {
        price.round().addSuffix(currency.symbol)
    }
    Text(
        text = priceText,
        style = textStyle,
        color = textColor,
        textAlign = TextAlign.End,
        modifier = modifier
    )
}

fun String.round(): String {
    val decimalPointIndex = indexOf(".")
    val firstDecimal = this[decimalPointIndex + 1].toString()
    return if (firstDecimal != "0") this
    else this.substring(0, decimalPointIndex)
}

fun String.addPrefix(prefix: String): String = "$prefix$this"
fun String.addSuffix(suffix: String): String = "$this$suffix"

@Preview
@Composable
private fun NeedItFashionCardPreview() {
    NeedItTheme {
        NeedItFashionInfoCard(
            title = "Scarf",
            subtitle = "",
            price = "35.0",
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
            price = "35.0",
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
            price = "200.0",
            currency = Currency(symbol = "€", isRightPositioned = true),
            description = "The blue one, not the red one"
        )
    }
}
