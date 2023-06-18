package com.carlosdiestro.needit.core.design_system.components.menu

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.theme.icons
import com.carlosdiestro.needit.core.design_system.theme.spacing

enum class SortType(@StringRes val labelId: Int) {
    LowToHigh(labelId = R.string.sorting_dialog_lowest_to_highest),
    HighToLow(labelId = R.string.sorting_dialog_highest_to_lowest)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NeedItBaseMenu(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(0.dp),
        dragHandle = {},
        modifier = Modifier
            .fillMaxWidth()
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItSortingMenu(
    sheetState: SheetState,
    optionSelected: SortType,
    onDismiss: () -> Unit,
    onOptionClick: (SortType) -> Unit
) {
    NeedItBaseMenu(
        sheetState = sheetState,
        onDismiss = onDismiss
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxs),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.m,
                    vertical = MaterialTheme.spacing.l
                )
        ) {
            SortType.values().forEach { type ->
                SortOption(
                    sortType = type,
                    backgroundColor = if (optionSelected == type) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                    textColor = if (optionSelected == type) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                    showIcon = optionSelected == type,
                    onOptionClick = onOptionClick
                )
            }
        }
    }
}

@Composable
private fun SortOption(
    sortType: SortType,
    backgroundColor: Color,
    textColor: Color,
    showIcon: Boolean,
    onOptionClick: (SortType) -> Unit
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .clickable { onOptionClick(sortType) }
            .background(backgroundColor)
            .padding(
                horizontal = MaterialTheme.spacing.m,
                vertical = MaterialTheme.spacing.s
            )
    ) {
        Text(
            text = context.getString(sortType.labelId),
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
        AnimatedVisibility(visible = showIcon) {
            Icon(imageVector = MaterialTheme.icons.Check, contentDescription = "Selected")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItItemActionMenu(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    actions: @Composable () -> Unit
) {
    NeedItBaseMenu(
        sheetState = sheetState,
        onDismiss = onDismiss
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.m)
        ) {
            actions()
        }
    }
}