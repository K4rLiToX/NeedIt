package com.carlosdiestro.design_system.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.avatars.NiAvatar
import com.carlosdiestro.design_system.buttons.NiDoubleButton
import com.carlosdiestro.design_system.buttons.NiFilledButton
import com.carlosdiestro.design_system.buttons.NiOutlinedButton
import com.carlosdiestro.design_system.i18n.Localization
import com.carlosdiestro.design_system.theme.dimensions
import com.carlosdiestro.design_system.theme.shape

data class NotificationPlo(
    val senderId: String,
    val imageUrl: String,
    val username: String,
) {
    val message: String
        @Composable get() = Localization.Notifications.FriendRequestMessage(name = username)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiFriendRequestCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    message: String,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        onClick = {},
        shape = MaterialTheme.shape.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        ),
        modifier = modifier
    ) {
        FriendRequestHeader(
            imageUrl = imageUrl,
            message = message
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingL))
        FriendRequestActions(
            onAccept = onAccept,
            onReject = onReject
        )
    }
}

@Composable
fun FriendRequestHeader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    message: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.spacingS),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .padding(top = MaterialTheme.dimensions.spacingL)
    ) {
        NiAvatar(
            imageUrl = imageUrl
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun FriendRequestActions(
    modifier: Modifier = Modifier,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    NiDoubleButton(
        leftButton = {
            NiOutlinedButton(
                label = Localization.Button.Reject,
                onClick = onReject
            )
        },
        rightButton = {
            NiFilledButton(
                label = Localization.Button.Accept,
                onClick = onAccept
            )
        },
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.dimensions.spacingM,
            alignment = Alignment.End
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimensions.spacingM)
            .padding(bottom = MaterialTheme.dimensions.spacingM)
    )
}