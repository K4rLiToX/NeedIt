package com.carlosdiestro.needit.core.design_system.components.dialogs

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.buttons.NeedItTextButton
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onRequestClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            val labelId =
                if (isPermanentlyDeclined) R.string.button_go_to_settings else R.string.button_request_again
            NeedItTextButton(
                labelId = labelId,
                onClick = {
                    if (isPermanentlyDeclined) onGoToAppSettingsClick() else onRequestClick()
                }
            )
        },
        icon = {
            Icon(
                imageVector = MaterialTheme.icons.Warning,
                contentDescription = "Warning"
            )
        },
        title = {
            Text(
                text = stringResource(
                    id = R.string.camera_permission_dialog_title,
                )
            )
        },
        text = {
            Text(
                text = stringResource(
                    id = permissionTextProvider.getDescription(isPermanentlyDeclined)
                )
            )
        },
        modifier = modifier,
        tonalElevation = 0.dp
    )
}

interface PermissionTextProvider {
    @get:StringRes
    val permission: Int
    fun getDescription(isPermanentlyDeclined: Boolean): Int
}

class CameraPermissionTextProvider : PermissionTextProvider {
    override val permission: Int
        get() = R.string.camera_permission_dialog_title

    override fun getDescription(isPermanentlyDeclined: Boolean): Int =
        if (isPermanentlyDeclined) R.string.camera_permission_dialog_description_declined
        else R.string.camera_permission_dialog_description_not_declined
}