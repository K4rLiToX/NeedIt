package com.carlosdiestro.needit.core.design_system.components.menus

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.avatars.NiAvatar
import com.carlosdiestro.needit.core.design_system.components.buttons.NiDoubleButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NiOutlinedButton
import com.carlosdiestro.needit.core.design_system.components.buttons.NiTextButton
import com.carlosdiestro.needit.core.design_system.theme.dimensions
import com.carlosdiestro.needit.core.design_system.theme.icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiAccountDialog(
    modifier: Modifier = Modifier,
    profilePictureUrl: String,
    username: String,
    email: String,
    isUserAnonymous: Boolean,
    onDismiss: () -> Unit,
    onManageAccountClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onFeedbackClick: () -> Unit,
    onReportBugClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfServiceClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
    ) {
        Column {
            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimensions.spacingM)
            )
            AccountHeader(
                profilePictureUrl = profilePictureUrl,
                username = username,
                email = email,
                isUserAnonymous = isUserAnonymous,
                onManageAccountClick = onManageAccountClick,
                onLoginClick = onLoginClick,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimensions.spacingXS)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )
            Spacer(modifier = Modifier.height(2.dp))
            AppActions(
                onSettingsClick = onSettingsClick,
                onFeedbackClick = onFeedbackClick,
                onReportBugClick = onReportBugClick,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimensions.spacingXS)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )
            LegalActions(
                onPrivacyPolicyClick = onPrivacyPolicyClick,
                onTermsOfServiceClick = onTermsOfServiceClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.dimensions.spacingL,
                        vertical = MaterialTheme.dimensions.spacingS
                    )
            )
        }

    }
}

@Composable
private fun Logo(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun AccountHeader(
    modifier: Modifier = Modifier,
    profilePictureUrl: String,
    username: String,
    email: String,
    isUserAnonymous: Boolean,
    onManageAccountClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.spacingM)
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
        Row(
            modifier = Modifier
                .padding(horizontal = 64.dp)
                .padding(
                    top = MaterialTheme.dimensions.spacingXS,
                    bottom = MaterialTheme.dimensions.spacingL
                )
        ) {
            NiOutlinedButton(
                labelId = if (!isUserAnonymous) R.string.button_manage_account else R.string
                    .button_login,
                onClick = if (!isUserAnonymous) onManageAccountClick else onLoginClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AppActions(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    onFeedbackClick: () -> Unit,
    onReportBugClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AppOption(
            icon = MaterialTheme.icons.Settings,
            labelId = R.string.settings_title,
            onClick = onSettingsClick
        )
        AppOption(
            icon = MaterialTheme.icons.Feedback,
            labelId = R.string.send_feedback_title,
            onClick = onFeedbackClick
        )
        AppOption(
            icon = MaterialTheme.icons.Bug,
            labelId = R.string.report_bug_title,
            onClick = onReportBugClick
        )
    }
}

@Composable
private fun AppOption(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes labelId: Int,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingM),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick() }
            .padding(
                horizontal = MaterialTheme.dimensions.spacingL,
                vertical = MaterialTheme.dimensions.spacingM
            )
            .fillMaxWidth()
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
}

@Composable
private fun LegalActions(
    modifier: Modifier = Modifier,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfServiceClick: () -> Unit
) {
    NiDoubleButton(
        leftButton = {
            NiTextButton(
                labelId = R.string.button_privacy_policy,
                onClick = onPrivacyPolicyClick
            )
        },
        rightButton = {
            NiTextButton(
                labelId = R.string.button_terms_of_service,
                onClick = onTermsOfServiceClick
            )
        },
        modifier = modifier
    )
}