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
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@Composable
fun UpsertRoute(
    onBackClick: () -> Unit,
    navigateHome: () -> Unit,
    viewModel: UpsertViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
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
        title = title,
        subtitle = subtitle,
        price = price,
        webUrl = webUrl,
        description = description,
        size = size,
        color = color,
        isbn = isbn,
        showSaveButton = viewModel.isFormFilledInCorrectly(),
        onBackClick = onBackClick,
        navigateHome = navigateHome,
        onSaveClick = viewModel::save,
        updateTitle = viewModel::updateTitle,
        updateSubtitle = viewModel::updateSubtitle,
        updatePrice = viewModel::updatePrice,
        updateWebUrl = viewModel::updateWebUrl,
        updateDescription = viewModel::updateDescription,
        updateSize = viewModel::updateSize,
        updateColor = viewModel::updateColor,
        updateIsbn = viewModel::updateIsbn,
    )
}

@Composable
private fun UpsertScreen(
    state: UpsertUiState,
    title: String,
    subtitle: String,
    price: String,
    webUrl: String,
    description: String,
    size: String,
    color: String,
    isbn: String,
    showSaveButton: Boolean,
    onBackClick: () -> Unit,
    navigateHome: () -> Unit,
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
                    if (showSaveButton) {
                        NeedItTextButton(
                            labelId = R.string.button_save,
                            onClick = {
                                onSaveClick()
                                navigateHome()
                            }
                        )
                    }
                }
            )
        }
    ) {
        UpsertContent(
            imageUrl = state.imageUrl,
            category = state.category,
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
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXL),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
    ) {
        ImageSection(imageUrl)
        BasicInformationSection(
            category = category,
            title = title,
            subtitle = subtitle,
            price = price,
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
    subtitle: String,
    price: String,
    updateTitle: (String) -> Unit,
    updateSubtitle: (String) -> Unit,
    updatePrice: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val labelId = if (category == WishCategory.Books) R.string.upsert_title_hint else
                R.string.upsert_name_hint

            OutlinedTextField(
                value = title,
                onValueChange = updateTitle,
                label = {
                    Text(
                        text = stringResource(id = labelId),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val labelId = if (category == WishCategory.Books) R.string.upsert_author_hint else R
                .string.upsert_brand_hint
            OutlinedTextField(
                value = subtitle,
                onValueChange = updateSubtitle,
                label = {
                    Text(
                        text = stringResource(id = labelId),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            OutlinedTextField(
                value = price,
                onValueChange = updatePrice,
                label = {
                    Text(
                        text = stringResource(id = labelId),
                        style = MaterialTheme.typography.bodySmall
                    )
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
                    Text(
                        text = stringResource(id = R.string.upsert_isbn_hint),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        else -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = size,
                    onValueChange = updateSize,
                    label = {
                        Text(
                            text = stringResource(id = R.string.upsert_size_hint),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                )
                OutlinedTextField(
                    value = color,
                    onValueChange = updateColor,
                    label = {
                        Text(
                            text = stringResource(id = R.string.upsert_color_hint),
                            style = MaterialTheme.typography.bodySmall
                        )
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
    description: String,
    updateWebUrl: (String) -> Unit,
    updateDescription: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = webUrl,
            onValueChange = updateWebUrl,
            label = {
                Text(
                    text = stringResource(id = R.string.upsert_website_link_hint),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            prefix = {
                Text(text = "https://")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = updateDescription,
            label = {
                Text(
                    text = stringResource(id = R.string.upsert_description_link),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}