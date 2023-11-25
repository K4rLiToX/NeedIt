package com.carlosdiestro.feature.upsert_wish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.carlosdiestro.design_system.buttons.NiTextButton
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.design_system.navigation.top_app_bar.NiTopAppBar
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpsertRoute(
    onBackClick: () -> Unit,
    onFinish: () -> Unit,
    viewModel: UpsertViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = rememberUpsertUiState()
    UpsertScreen(
        dataState = dataState,
        uiState = uiState,
        title = viewModel.title,
        subtitle = viewModel.subtitle,
        price = viewModel.price,
        webUrl = viewModel.webUrl,
        description = viewModel.description,
        size = viewModel.size.orEmpty(),
        color = viewModel.color.orEmpty(),
        isbn = viewModel.isbn.orEmpty(),
        saveButtonEnabled = viewModel.saveButtonEnabled,
        onBackClick = onBackClick,
        navigateHome = onFinish,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UpsertScreen(
    dataState: UpsertDataState,
    uiState: UpsertUiState,
    title: String,
    subtitle: String,
    price: String,
    webUrl: String,
    description: String,
    size: String,
    color: String,
    isbn: String,
    saveButtonEnabled: Boolean,
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
            NiTopAppBar(
                title = "",
                onNavigationClick = onBackClick,
                scrollBehavior = uiState.scrollBehavior,
                actions = {
                    NiTextButton(
                        label = Localization.Button.Save,
                        enabled = saveButtonEnabled,
                        onClick = {
                            onSaveClick()
                            navigateHome()
                        }
                    )
                }
            )
        },
        modifier = Modifier
            .nestedScroll(uiState.scrollBehavior.nestedScrollConnection)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingXXL),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .verticalScroll(uiState.scrollState)
        ) {
            val isSpecificInformationNeeded = dataState.category in listOf(
                WishCategoryPlo.Clothes,
                WishCategoryPlo.Footwear,
                WishCategoryPlo.Books
            )
            AsyncImage(
                model = dataState.imageLocalPath,
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shape.medium)
                    .height(221.dp)
            )
            CommonInformation(
                title = title,
                titleHint = dataState.titleHint,
                subtitle = subtitle,
                subtitleHint = dataState.subtitleHint,
                price = price,
                updateTitle = updateTitle,
                updateSubtitle = updateSubtitle,
                updatePrice = updatePrice
            )
            if (isSpecificInformationNeeded) {
                SpecificInformation(
                    category = dataState.category,
                    size = size,
                    color = color,
                    isbn = isbn,
                    updateSize = updateSize,
                    updateColor = updateColor,
                    updateIsbn = updateIsbn
                )
            }
            AdditionalInformation(
                webUrl = webUrl,
                description = description,
                updateWebUrl = updateWebUrl,
                updateDescription = updateDescription
            )
        }
    }
}

@Composable
private fun CommonInformation(
    title: String,
    titleHint: String,
    subtitle: String,
    subtitleHint: String,
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
            OutlinedTextField(
                value = title,
                onValueChange = updateTitle,
                label = {
                    Text(
                        text = titleHint,
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
            OutlinedTextField(
                value = subtitle,
                onValueChange = updateSubtitle,
                label = {
                    Text(
                        text = subtitleHint,
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
                        text = Localization.Upsert.PriceHint,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun SpecificInformation(
    category: WishCategoryPlo,
    size: String,
    color: String,
    isbn: String,
    updateSize: (String) -> Unit,
    updateColor: (String) -> Unit,
    updateIsbn: (String) -> Unit
) {
    when (category) {
        WishCategoryPlo.Books -> {
            OutlinedTextField(
                value = isbn,
                onValueChange = updateIsbn,
                label = {
                    Text(
                        text = Localization.Upsert.IsbnHint,
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
                            text = Localization.Upsert.SizeHint,
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
                            text = Localization.Upsert.ColorHint,
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
private fun AdditionalInformation(
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
                    text = Localization.Upsert.WebHint,
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
                    text = Localization.Upsert.DescriptionHint,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}