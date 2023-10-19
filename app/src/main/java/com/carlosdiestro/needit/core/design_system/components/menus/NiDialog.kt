package com.carlosdiestro.needit.core.design_system.components.menus

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NiButtonSpecs
import com.carlosdiestro.needit.core.design_system.components.buttons.NiFilledButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiDialog(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes bodyId: Int,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shape = NiMenuSpecs.RoundedCorners,
        dragHandle = NiMenuSpecs.NoDragHandle,
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
    ) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .padding(top = MaterialTheme.dimensions.spacingL)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))
        Text(
            text = stringResource(id = bodyId),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingL),
            verticalAlignment = Alignment.CenterVertically,
            content = actions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimensions.spacingM)
                .padding(bottom = MaterialTheme.dimensions.spacingM)
        )
    }
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
            sheetState = sheetState,
            onDismiss = {}
        ) {
            NiFilledButton(
                labelId = R.string.button_reject,
                colors = NiButtonSpecs.Color.neutral(),
                height = NiButtonSpecs.Height.Large,
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.48F)
            )
            NiFilledButton(
                labelId = R.string.button_continue,
                colors = NiButtonSpecs.Color.error(),
                height = NiButtonSpecs.Height.Large,
                onClick = {},
                modifier = Modifier.weight(1F)
            )
        }
    }
}
