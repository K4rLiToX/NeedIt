package com.carlosdiestro.needit.features.account

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosdiestro.design_system.avatars.NiAvatar
import com.carlosdiestro.design_system.buttons.NiDoubleButton
import com.carlosdiestro.design_system.buttons.NiOutlinedButton
import com.carlosdiestro.design_system.buttons.NiTextButton
import com.carlosdiestro.needit.R
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.icons
import com.carlosdiestro.design_system.theme.shape

@Composable
fun AccountDialogRoute(
    onDismiss: () -> Unit,
    onSignOutClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    AccountDialog(
        onDismiss = onDismiss,
        onSignOutClick = onSignOutClick,
        onSettingsClick = onSettingsClick
    )
}

@Composable
private fun AccountDialog(
    onDismiss: () -> Unit,
    onSignOutClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val dataState by viewModel.state.collectAsStateWithLifecycle()
    val googleIntent by viewModel.googleSignInIntentState.collectAsStateWithLifecycle()
    val signInError by viewModel.signInErrorState.collectAsStateWithLifecycle()
    val uiState = rememberAccountDialogState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.linkAccount(result.data ?: return@rememberLauncherForActivityResult)
            }
        }
    )

    LaunchedEffect(signInError) {
        signInError?.let { error ->
            Toast.makeText(
                uiState.context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(googleIntent) {
        if (googleIntent != null) {
            launcher.launch(
                IntentSenderRequest.Builder(
                    intentSender = googleIntent!!
                ).build()
            )
        }
    }

    AccountDialog(
        uiState = uiState,
        dataState = dataState,
        onDismiss = onDismiss,
        requestGoogleSignInIntent = viewModel::requestGoogleSignInIntent,
        onSignOutClick = {
            viewModel.signOut()
            onSignOutClick()
        },
        onSettingsClick = onSettingsClick
    )
}

@Composable
private fun AccountDialog(
    uiState: AccountDialogUiState,
    dataState: AccountDataState,
    onDismiss: () -> Unit,
    requestGoogleSignInIntent: () -> Unit,
    onSignOutClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    when (dataState) {
        AccountDataState.Loading -> Unit
        is AccountDataState.Success -> {
            AccountDialog(
                uiState = uiState,
                username = dataState.account.username,
                email = dataState.account.email,
                profilePictureUrl = dataState.account.profilePictureUrl,
                isAnonymous = dataState.account.isAnonymous,
                onDismiss = onDismiss,
                onAccountActionClick = {
                    if (dataState.account.isAnonymous) requestGoogleSignInIntent()
                    else onSignOutClick()
                },
                header = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                accountExtras = {
                    AppOption(
                        icon = MaterialTheme.icons.Birthday,
                        labelId = R.string.profile_birthday,
                        value = dataState.account.birthday,
                        onClick = {}
                    )
                    AppOption(
                        icon = MaterialTheme.icons.Currency,
                        labelId = R.string.profile_currency,
                        value = dataState.account.currency,
                        onClick = {}
                    )
                },
                appOptions = {
                    AppOption(
                        icon = MaterialTheme.icons.Settings,
                        labelId = R.string.settings_title,
                        onClick = onSettingsClick
                    )
                    AppOption(
                        icon = MaterialTheme.icons.Feedback,
                        labelId = R.string.send_feedback_title,
                        onClick = {}
                    )
                    AppOption(
                        icon = MaterialTheme.icons.Bug,
                        labelId = R.string.report_bug_title,
                        onClick = {}
                    )
                },
                footer = {
                    NiDoubleButton(
                        leftButton = {
                            NiTextButton(
                                labelId = R.string.button_privacy_policy,
                                onClick = {}
                            )
                        },
                        rightButton = {
                            NiTextButton(
                                labelId = R.string.button_terms_of_service,
                                onClick = {}
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.dimensions.spacingL,
                                vertical = MaterialTheme.dimensions.spacingS
                            )
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountDialog(
    modifier: Modifier = Modifier,
    uiState: AccountDialogUiState,
    username: String,
    email: String,
    profilePictureUrl: String,
    isAnonymous: Boolean,
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
            .clip(MaterialTheme.shape.large)
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
                isUserAnonymous = isAnonymous,
                shouldShowAccountExtras = uiState.shouldShowAccountExtras,
                onAccountInformationClick = uiState::setShowAccountExtra,
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
            .clip(MaterialTheme.shape.topMedium)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onAccountInformationClick(!shouldShowAccountExtras) }
            .padding(MaterialTheme.dimensions.spacingM)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            .clip(MaterialTheme.shape.bottomMedium)
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
private fun AppOption(
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