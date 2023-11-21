package com.carlosdiestro.design_system.menus

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.buttons.NiButtonSpecs
import com.carlosdiestro.design_system.buttons.NiFilledButton

@Composable
fun NiDialog(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes bodyId: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = stringResource(id = bodyId),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            NiFilledButton(
                labelId = -1,
                colors = NiButtonSpecs.Color.error(),
                height = NiButtonSpecs.Height.Large,
                onClick = onConfirm
            )
        },
        dismissButton = {
            NiFilledButton(
                labelId = -1,
                colors = NiButtonSpecs.Color.neutral(),
                height = NiButtonSpecs.Height.Large,
                onClick = onDismiss
            )
        },
        shape = NiMenuSpecs.RoundedCorners,
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiDialogPreview() {
    MaterialTheme {
        NiDialog(
            titleId = -1,
            bodyId = -1,
            onDismiss = {},
            onConfirm = {}
        )
    }
}
