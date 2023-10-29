package com.carlosdiestro.needit.core.design_system.components.menus

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatar
import com.carlosdiestro.needit.core.design_system.components.buttons.NiOutlinedButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@Composable
fun rememberNiAccountDialogState(): NiAccountDialogState {
    return remember {
        NiAccountDialogState()
    }
}

@Stable
class NiAccountDialogState {

    var shouldShowAccountExtras by mutableStateOf(false)
        private set

    fun setShowAccountExtra(show: Boolean) {
        shouldShowAccountExtras = show
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiAccountDialog(
    modifier: Modifier = Modifier,
    state: NiAccountDialogState,
    username: String,
    email: String,
    profilePictureUrl: String,
    isUserAnonymous: Boolean,
    onDismiss: () -> Unit,
    onAccountActionClick: () -> Unit,
    header: @Composable RowScope.() -> Unit = {},
    accountExtras: @Composable ColumnScope.() -> Unit = {},
    appOptions: @Composable ColumnScope.() -> Unit = {},
    footer: @Composable () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
    ) {
        Column {
            AccountDialogHeader(
                content = header
            )
            AccountDialogAccount(
                profilePictureUrl = profilePictureUrl,
                username = username,
                email = email,
                isUserAnonymous = isUserAnonymous,
                shouldShowAccountExtras = state.shouldShowAccountExtras,
                onAccountInformationClick = state::setShowAccountExtra,
                onAccountActionClick = onAccountActionClick,
                accountExtras = accountExtras
            )
            Spacer(modifier = Modifier.height(2.dp))
            AccountDialogBody(
                content = appOptions
            )
            AccountDialogFooter(
                content = footer
            )
        }
    }
}

@Composable
private fun AccountDialogHeader(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimensions.spacingM),
        content = content
    )
}

@Composable
private fun AccountDialogAccount(
    profilePictureUrl: String,
    username: String,
    email: String,
    isUserAnonymous: Boolean,
    shouldShowAccountExtras: Boolean,
    onAccountInformationClick: (Boolean) -> Unit,
    onAccountActionClick: () -> Unit,
    accountExtras: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingXS)
            .fillMaxWidth()
    ) {
        AccountDialogAccountHeader(
            profilePictureUrl = profilePictureUrl,
            username = username,
            email = email,
            isUserAnonymous = isUserAnonymous,
            shouldShowAccountExtras = shouldShowAccountExtras,
            onAccountInformationClick = onAccountInformationClick
        )
        if (shouldShowAccountExtras) Spacer(modifier = Modifier.height(2.dp))
        AccountDialogAccountExtraInformation(
            isUserAnonymous = isUserAnonymous,
            shouldShowAccountExtras = shouldShowAccountExtras,
            onAccountActionClick = onAccountActionClick,
            content = accountExtras
        )
    }
}

@Composable
fun AccountDialogAccountAction(
    isUserAnonymous: Boolean,
    onAccountActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 64.dp)
            .padding(
                top = MaterialTheme.dimensions.spacingXS,
                bottom = MaterialTheme.dimensions.spacingL
            )
    ) {
        NiOutlinedButton(
            labelId = if (!isUserAnonymous) R.string.button_logout else R.string
                .button_login,
            onClick = onAccountActionClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AccountDialogAccountHeader(
    profilePictureUrl: String,
    username: String,
    email: String,
    isUserAnonymous: Boolean,
    shouldShowAccountExtras: Boolean,
    onAccountInformationClick: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onAccountInformationClick(!shouldShowAccountExtras) }
            .padding(MaterialTheme.dimensions.spacingM)
    ) {
        Row {
            NiAvatar(
                imageUrl = profilePictureUrl
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimensions.spacingXS))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (!isUserAnonymous) {
                    Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingXXS))
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        Icon(
            imageVector = if (shouldShowAccountExtras) MaterialTheme
                .icons.ArrowUp else
                MaterialTheme
                    .icons.ArrowDown,
            contentDescription = ""
        )
    }
}

@Composable
private fun AccountDialogAccountExtraInformation(
    isUserAnonymous: Boolean,
    shouldShowAccountExtras: Boolean,
    onAccountActionClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    AnimatedVisibility(
        visible = shouldShowAccountExtras,
        enter = expandVertically(expandFrom = Alignment.Top),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            content()
            AccountDialogAccountAction(
                isUserAnonymous = isUserAnonymous,
                onAccountActionClick = onAccountActionClick
            )
        }
    }
}

@Composable
private fun AccountDialogBody(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingXS)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(MaterialTheme.colorScheme.surface),
        content = content
    )
}

@Composable
private fun AccountDialogFooter(
    content: @Composable () -> Unit
) {
    Row {
        content()
    }
}

@Composable
fun AppOption(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes labelId: Int,
    value: String = "",
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick() }
            .padding(
                horizontal = MaterialTheme.dimensions.spacingL,
                vertical = MaterialTheme.dimensions.spacingM
            )
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}