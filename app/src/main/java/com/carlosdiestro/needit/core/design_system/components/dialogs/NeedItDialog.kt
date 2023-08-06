package com.carlosdiestro.needit.core.design_system.components.dialogs

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItFilledButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeedItDialog(
    @StringRes titleId: Int,
    @StringRes bodyId: Int,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onAccept: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            NeedItFilledButton(
                labelId = R.string.button_accept,
                onClick = onAccept
            )
        },
        title = {
            Text(text = stringResource(id = titleId))
        },
        text = {
            Text(text = stringResource(id = bodyId))
        }
    )
}