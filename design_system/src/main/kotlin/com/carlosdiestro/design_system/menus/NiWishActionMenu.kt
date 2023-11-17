package com.carlosdiestro.design_system.menus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiWishActionMenu(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = NiMenuSpecs.StraightCorners,
        dragHandle = NiMenuSpecs.NoDragHandle,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingL)
                .padding(top = MaterialTheme.dimensions.spacingL)
                .padding(bottom = MaterialTheme.dimensions.spacingXL)
        ) {
            actions()
        }
    }
}