package com.carlosdiestro.needit.core.design_system.components.menus

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton

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
                labelId = R.string.button_continue,
                colors = NiButtonSpecs.Color.error(),
                height = NiButtonSpecs.Height.Large,
                onClick = onConfirm
            )
        },
        dismissButton = {
            NiFilledButton(
                labelId = R.string.button_reject,
                colors = NiButtonSpecs.Color.neutral(),
                height = NiButtonSpecs.Height.Large,
                onClick = onDismiss
            )
        },
        shape = NiMenuSpecs.RoundedCorners,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NiDialogPreview() {
    MaterialTheme {
        val sheetState = rememberModalBottomSheetState()
        NiDialog(
            titleId = R.string.delete_dialog_title,
            bodyId = R.string.delete_dialog_body,
            onDismiss = {},
            onConfirm = {}
        )
    }
}
