package com.carlosdiestro.needit.core.design_system.components.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.ButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItDialog(
    @StringRes titleId: Int,
    @StringRes bodyId: Int,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(24.dp),
        dragHandle = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
    ) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.spacingL)
        )
        Text(
            text = stringResource(id = bodyId),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.spacingL)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingL)
                .padding(bottom = MaterialTheme.dimensions.spacingXL)
                .padding(top = MaterialTheme.dimensions.spacingL)
        ) {
            NeedItFilledButton(
                labelId = R.string.button_reject,
                onClick = onDismiss,
                colors = ButtonSpecs.surfaceVariantColors(),
                size = ButtonSpecs.LargeHeight,
                modifier = Modifier
                    .fillMaxWidth(0.48f)
            )
            NeedItFilledButton(
                labelId = R.string.button_accept,
                onClick = onAccept,
                colors = ButtonSpecs.errorColors(),
                size = ButtonSpecs.LargeHeight,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}