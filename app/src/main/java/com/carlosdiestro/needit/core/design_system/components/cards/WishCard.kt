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
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledIconButton
import com.carlosdiestro.needit.core.design_system.components.extensions.gradient
import com.carlosdiestro.needit.core.design_system.components.images.NeedItImageContainer
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icon
import com.carlosdiestro.needit.core.design_system.theme.iconButton
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing
import kotlin.math.roundToInt

class WishPLO(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val price: Double,
    val currency: Currency,
    val isShared: Boolean,
)

class Currency(
    val symbol: String,
    val isRightPositioned: Boolean
)

@Composable
fun WishCard(
    wish: WishPLO,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    NeedItImageContainer(
        imageUrl = wish.imageUrl,
        contentDescription = wish.title,
        onClick = onClick,
        onLongClick = onLongClick,
    ) {
        WishCardContent(wish = wish)
    }
}

@Composable
private fun WishCardContent(
    wish: WishPLO
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        IconSection(isShared = wish.isShared)
        InformationSection(
            title = wish.title,
            price = wish.price,
            currency = wish.currency
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
private fun PriceText(
    price: Double,
    currency: Currency
) {
    val priceText = if (!currency.isRightPositioned) {
        price.toString().round().addPrefix(currency.symbol)
    } else {
        price.toString().round().addSuffix(currency.symbol)
    }
    Text(
        text = priceText,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary
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
                wish = wish1,
                onClick = {},
                onLongClick = {}
            )
            WishCard(
                wish = wish2,
                onClick = {},
                onLongClick = {}
            )
        }
    }
}