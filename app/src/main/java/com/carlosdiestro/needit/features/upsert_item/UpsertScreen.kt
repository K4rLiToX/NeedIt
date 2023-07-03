package com.carlosdiestro.needit.features.upsert_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItTextButton
import com.carlosdiestro.needit.core.design_system.components.navigation.NeedItTopAppBar
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.design_system.theme.spacing

@Composable
fun UpsertRoute(
    onBackClick: () -> Unit,
    viewModel: UpsertViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val titleHasError by viewModel.titleHasError.collectAsStateWithLifecycle()
    val subtitleHasError by viewModel.subtitleHasError.collectAsStateWithLifecycle()
    val priceHasError by viewModel.priceHasError.collectAsStateWithLifecycle()
    val webUrlHasError by viewModel.webUrlHasError.collectAsStateWithLifecycle()
    val title = viewModel.title
    val subtitle = viewModel.subtitle
    val price = viewModel.price
    val webUrl = viewModel.webUrl
    val description = viewModel.description
    val size = viewModel.size
    val color = viewModel.color
    val isbn = viewModel.isbn
    UpsertScreen(
        state = state,
        titleHasError = titleHasError,
        subtitleHasError = subtitleHasError,
        priceHasError = priceHasError,
        webUrlHasError = webUrlHasError,
        title = title,
        subtitle = subtitle,
        price = price,
        webUrl = webUrl,
        description = description,
        size = size,
        color = color,
        isbn = isbn,
        onBackClick = onBackClick,
        onSaveClick = {},
        updateTitle = viewModel::updateTitle,
        updateSubtitle = viewModel::updateSubtitle,
        updatePrice = viewModel::updatePrice,
        updateWebUrl = viewModel::updateWebUrl,
        updateDescription = viewModel::updateDescription,
        updateSize = viewModel::updateSize,
        updateColor = viewModel::updateColor,
        updateIsbn = viewModel::updateIsbn
    )
}

@Composable
private fun UpsertScreen(
    state: UpsertUiState,
    titleHasError: Boolean,
    subtitleHasError: Boolean,
    priceHasError: Boolean,
    webUrlHasError: Boolean,
    title: String,
    subtitle: String,
    price: String,
    webUrl: String,
    description: String,
    size: String,
    color: String,
    isbn: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    updateTitle: (String) -> Unit,
    updateSubtitle: (String) -> Unit,
    updatePrice: (String) -> Unit,
    updateWebUrl: (String) -> Unit,
    updateDescription: (String) -> Unit,
    updateSize: (String) -> Unit,
    updateColor: (String) -> Unit,
    updateIsbn: (String) -> Unit
) {
    Scaffold(
        topBar = {
            NeedItTopAppBar(
                onNavigateClick = onBackClick,
                actions = {
                    NeedItTextButton(
                        labelId = R.string.button_save,
                        onClick = onSaveClick
                    )
                }
            )
        }
    ) {
        UpsertContent(
            imageUrl = state.imageUrl,
            category = state.category,
            titleHasError = titleHasError,
            subtitleHasError = subtitleHasError,
            priceHasError = priceHasError,
            webUrlHasError = webUrlHasError,
            title = title,
            subtitle = subtitle,
            price = price,
            webUrl = webUrl,
            description = description,
            size = size,
            color = color,
            isbn = isbn,
            updateTitle = updateTitle,
            updateSubtitle = updateSubtitle,
            updatePrice = updatePrice,
            updateWebUrl = updateWebUrl,
            updateDescription = updateDescription,
            updateSize = updateSize,
            updateColor = updateColor,
            updateIsbn = updateIsbn,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
private fun UpsertContent(
    imageUrl: String,
    category: WishCategory,
    titleHasError: Boolean,
    subtitleHasError: Boolean,
    priceHasError: Boolean,
    webUrlHasError: Boolean,
    title: String,
    subtitle: String,
    price: String,
    webUrl: String,
    description: String,
    size: String,
    color: String,
    isbn: String,
    updateTitle: (String) -> Unit,
    updateSubtitle: (String) -> Unit,
    updatePrice: (String) -> Unit,
    updateWebUrl: (String) -> Unit,
    updateDescription: (String) -> Unit,
    updateSize: (String) -> Unit,
    updateColor: (String) -> Unit,
    updateIsbn: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacing.m)
    ) {
        ImageSection(imageUrl)
        BasicInformationSection(
            category = category,
            title = title,
            titleHasError = titleHasError,
            subtitle = subtitle,
            subtitleHasError = subtitleHasError,
            price = price,
            priceHasError = priceHasError,
            updateTitle = updateTitle,
            updateSubtitle = updateSubtitle,
            updatePrice = updatePrice
        )
        if (category == WishCategory.Clothes || category == WishCategory.Footwear || category ==
            WishCategory.Books
        ) {
            SpecificInformationSection(
                category = category,
                size = size,
                color = color,
                isbn = isbn,
                updateSize = updateSize,
                updateColor = updateColor,
                updateIsbn = updateIsbn
            )
        }
        AdditionalInformationSection(
            webUrl = webUrl,
            webUrlHasError = webUrlHasError,
            description = description,
            updateWebUrl = updateWebUrl,
            updateDescription = updateDescription
        )
    }
}

@Composable
private fun ImageSection(
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .height(221.dp)
    )
}

@Composable
private fun BasicInformationSection(
    category: WishCategory,
    title: String,
    titleHasError: Boolean,
    subtitle: String,
    subtitleHasError: Boolean,
    price: String,
    priceHasError: Boolean,
    updateTitle: (String) -> Unit,
    updateSubtitle: (String) -> Unit,
    updatePrice: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val label = stringResource(
                id = if (category == WishCategory.Books) R.string.upsert_title_hint else
                    R.string.upsert_name_hint
            )
            OutlinedTextField(
                value = title,
                onValueChange = updateTitle,
                isError = titleHasError,
                label = {
                    Text(text = label)
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.l),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val label = stringResource(
                id = if (category == WishCategory.Books) R.string.upsert_author_hint else R
                    .string.upsert_brand_hint
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = updateSubtitle,
                isError = subtitleHasError,
                label = {
                    Text(text = label)
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            OutlinedTextField(
                value = price,
                onValueChange = updatePrice,
                isError = priceHasError,
                label = {
                    Text(text = stringResource(id = R.string.upsert_price_hint))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun SpecificInformationSection(
    category: WishCategory,
    size: String,
    color: String,
    isbn: String,
    updateSize: (String) -> Unit,
    updateColor: (String) -> Unit,
    updateIsbn: (String) -> Unit
) {
    when (category) {
        WishCategory.Books -> {
            OutlinedTextField(
                value = isbn,
                onValueChange = updateIsbn,
                label = {
                    Text(text = stringResource(id = R.string.upsert_isbn_hint))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        else -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.l),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = size,
                    onValueChange = updateSize,
                    label = {
                        Text(text = stringResource(id = R.string.upsert_size_hint))
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                )
                OutlinedTextField(
                    value = color,
                    onValueChange = updateColor,
                    label = {
                        Text(text = stringResource(id = R.string.upsert_color_hint))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AdditionalInformationSection(
    webUrl: String,
    webUrlHasError: Boolean,
    description: String,
    updateWebUrl: (String) -> Unit,
    updateDescription: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = webUrl,
            onValueChange = updateWebUrl,
            isError = webUrlHasError,
            label = {
                Text(text = stringResource(id = R.string.upsert_website_link_hint))
            },
            prefix = {
                Text(text = "https://")
            }
        )
        OutlinedTextField(
            value = description,
            onValueChange = updateDescription,
            label = {
                Text(
                    text = stringResource(
                        id = R.string.upsert_description_link
                    )
                )
            }
        )
    }
}