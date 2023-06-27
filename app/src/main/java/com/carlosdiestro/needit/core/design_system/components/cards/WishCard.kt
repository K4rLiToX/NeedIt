package com.carlosdiestro.needit.core.design_system.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.extensions.gradient
import com.carlosdiestro.needit.core.design_system.components.images.NeedItImageContainer
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icon
import com.carlosdiestro.needit.core.design_system.theme.iconButton
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing

class WishPLO(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val price: Double,
    val currency: Currency,
    val isShared: Boolean,
    val category: WishCategory
)

class Currency(
    val symbol: String,
    val isRightPositioned: Boolean
)

@Composable
fun WishCard(
    title: String,
    imageUrl: String,
    price: Double,
    currency: Currency,
    isShared: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    NeedItImageContainer(
        imageUrl = imageUrl,
        contentDescription = title,
        width = 172.dp,
        height = 220.dp,
        onClick = onClick,
        onLongClick = onLongClick,
        cardContent = {
            WishCardContent(
                title = title,
                price = price,
                currency = currency,
                isShared = isShared
            )
        }
    )
}

@Composable
private fun WishCardContent(
    title: String,
    price: Double,
    currency: Currency,
    isShared: Boolean
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        IconSection(isShared = isShared)
        InformationSection(
            title = title,
            price = price,
            currency = currency
        )
    }
}

@Composable
private fun IconSection(
    isShared: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.s)
    ) {
        if (isShared) {
            NeedItFilledIconButton(
                icon = MaterialTheme.icons.Share,
                containerSize = MaterialTheme.dimensions.iconButton.small,
                iconSize = MaterialTheme.dimensions.icon.extraSmall,
                onClick = {},
            )
        }
    }
}

@Composable
private fun InformationSection(
    title: String,
    price: Double,
    currency: Currency
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .gradient(MaterialTheme.colorScheme.onSurface)
            .padding(MaterialTheme.spacing.s)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        PriceText(
            price = price,
            currency = currency
        )
    }
}

@Composable
fun PriceText(
    price: Double,
    currency: Currency,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    val priceText = if (!currency.isRightPositioned) {
        price.toString().round().addPrefix(currency.symbol)
    } else {
        price.toString().round().addSuffix(currency.symbol)
    }
    Text(
        text = priceText,
        style = textStyle,
        color = textColor
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
private fun WishCardPreview() {
    val currency1 = Currency(
        symbol = "€",
        isRightPositioned = true
    )
    val currency2 = Currency(
        symbol = "$",
        isRightPositioned = false
    )
    val wish1 = WishPLO(
        id = 0,
        imageUrl = "",
        title = "Chanel",
        price = 50.0,
        currency = currency1,
        isShared = false
    )
    val wish2 = WishPLO(
        id = 1,
        imageUrl = "",
        title = "Chanel",
        price = 50.4,
        currency = currency2,
        isShared = true
    )
    NeedItTheme {
        Column {
            WishCard(
                title = wish1.title,
                imageUrl = wish1.imageUrl,
                price = wish1.price,
                currency = wish1.currency,
                isShared = wish1.isShared,
                onClick = {},
                onLongClick = {}
            )
            WishCard(
                title = wish2.title,
                imageUrl = wish2.imageUrl,
                price = wish2.price,
                currency = wish2.currency,
                isShared = wish2.isShared,
                onClick = {},
                onLongClick = {}
            )
        }
    }
}