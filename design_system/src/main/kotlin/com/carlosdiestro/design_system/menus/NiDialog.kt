package com.carlosdiestro.design_system.menus

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.design_system.buttons.NiButtonSpecs
import com.carlosdiestro.design_system.buttons.NiFilledButton
import com.carlosdiestro.design_system.i18n.Localization

@Composable
fun NiDialog(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            NiFilledButton(
                label = Localization.Button.Remove,
                colors = NiButtonSpecs.Color.error(),
                height = NiButtonSpecs.Height.Large,
                onClick = onConfirm
            )
        },
        dismissButton = {
            NiFilledButton(
                label = Localization.Button.Cancel,
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
            title = Localization.DeleteDialog.Title,
            body = Localization.DeleteDialog.Body,
            onDismiss = {},
            onConfirm = {}
        )
    }
}
